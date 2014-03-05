; (ns fux.fux)
(require '[overtone.live :as ot])

; simple overtone instrument with fixed params
(ot/definst hum [note 70 amp 0.2]
   (let [freq (ot/midicps note)]
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 1 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 )))


; takes a vector of either midi values or vectors of midi values.
(defn play [x]
  )

(defn equaltemp [a, acc]
  ([]
    (equaltemp [440.0 {}]))
    (if (< a 20000.0)
      (acc 0 a)
      (equaltemp
        (* a (.pow 2.0 (/ 1.0 12.0)
        acc)))))




(defn playall
  ([notes]
    (playall 900 notes))
  ([inc notes]
    (map (fn [time note]
           (ot/at (+ (ot/now) time)
             (hum (harmonize note))
             (hum note)))
         (range 0 (* inc (count notes)) inc)
         notes)))

(source ot/at)

(playall cf)


(defn play [last current]
  (hum last)
  ; (hum (harmonize last))
  current
)

(defn harmonize [x]
    (rand-nth (vector (+ x 7) (+ x 12) (+ x 4)))
  )

(reduce play (conj cf 0))
(ot/at (+ (ot/now) 900) (hum 60))

(def cf [60 64 62 60 65 64 67 65 64 62 60])

(def cf [62 65 64 62 67 65 69 67 65 64 62])
(def cf [60 67 72 75 63])
(def cf [60 61 62 62])


; scratch pad

(def x [1 2 3 4 5 [6]])
(map vector (filter  x))

(= clojure.lang.PersistentVector (type [8]))

(type [8])

(+ 1 1)

(source ot/now)
