(ns fux.fux
(:require [overtone.live :as ot]))

;------------------------------------
; returns a list of all available frequencies in equal temperent. 440.0 hz tuning note
;------------------------------------

; is it appropriate to hold this helper function outside of the only place it is being called?
(defn lower [note]
  (if (< note 20.0)
    note
    (lower (/ note 2))))

; testing multiple dispatch - is it necessary to explicitely do all three?
(defn equaltemp
  ([] (equaltemp (lower 440.0) (vector)))
  ([a] (equaltemp (lower a) (vector)))
  ([a acc]
  (if (> a 20000.0)
    (vec(reverse acc))
    (equaltemp (* a (Math/pow 2.0 (/ 1.0 12.0))) (cons a acc)))))


(equaltemp)

; addressing by index:
((equaltemp) 51)
(ot/midi->hz 60)

(equaltemp)
(type (equaltemp))

;------------------------------------
; simple overtone instruments with fixed params except for note/freq
;------------------------------------

; takes a note value and maps it to my frequency list to get frequency
(ot/definst freq [freq 261.625 amp 0.6]
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 1 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 ))

; takes a midi note value and produces a freq from it
(ot/definst midi [note 60 amp 0.6]
   (let [freq (ot/midicps note)]
      (println freq)
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 1 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 )))

; takes a note value and maps it to my frequency list to get frequency,
; then sends that along to the freq instrument that has been defined

(ot/definst tinn [note 60 amp 0.6]
  ; (println (int (:value note)))
   (let [freq ((equaltemp) (int(:value note)))]
      (println freq)
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 1 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 )))

; (defn tinn
;   ([] (tinn 60))
;   ([note]
;     (freq ((equaltemp) (- note 9)))))


(tinn 60)
(midi 70)
(freq)

(midi 66)
(freq 440)
(tinn 69)

;------------------------------------
; plays notes from a vector in sequence
;------------------------------------

(defn rand-harmonize [x]
    (rand-nth (vector (+ x 7) (+ x 12) (+ x 4)))
  )


(defn playall
  ([notes]
    (playall 900 notes))
  ([inc notes]
    (map (fn [time note]
           (ot/at (+ (ot/now) time)
             (midi (rand-harmonize note))
             (midi note)))
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
