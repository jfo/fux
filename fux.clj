;------------------------------------
; simple overtone instrument with fixed params
;------------------------------------

(require '[overtone.live :as ot])

(ot/definst hum [note 70 amp 0.2]
   (let [freq (ot/midicps note)]
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 1 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 )))



;------------------------------------
; returns a list of all available frequencies in equal temperent. 440.0 hz tuning note
;------------------------------------

(defn lower [note]
  (if (< note 20.0)
    note
    (lower (/ note 2))))

(defn equaltemp [a acc]
  (if (> a 20000.0)
    acc
    (equaltemp (* a (Math/pow 2.0 (/ 1.0 12.0))) (cons a acc))))

(reverse (equaltemp (lower 440.0) (list)))




;------------------------------------
; plays notes from a vector in sequence
;------------------------------------
; (defn rand-harmonize [x]
;     (rand-nth (vector (+ x 7) (+ x 12) (+ x 4)))
;   )

(defn playall
  ([notes]
    (playall 900 notes))
  ([inc notes]
    (map (fn [time note]
           (ot/at (+ (ot/now) time)
             ; (hum (rand-harmonize note))
             (hum note)))
         (range 0 (* inc (count notes)) inc)
         notes)))


(def cf [62 65 64 62 67 65 69 67 65 64 62])
(def cf [60 64 62 60 65 64 67 65 64 62 60])
(def cf [60 67 72 75 63])
(def cf [60 61 62 62])

(playall cf)




(defn play [last current]
  (hum last)
  ; (hum (harmonize last))
  current
)


(reduce play (conj cf 0))
(ot/at (+ (ot/now) 900) (hum 60))



; scratch pad

(= clojure.lang.PersistentVector (type [8]))

(type [8])

(+ 1 1)

(source ot/now)
