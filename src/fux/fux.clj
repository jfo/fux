(ns fux.player
  (:require [overtone.live :as ot]))
;------------------------------------
; plays notes from a vector in sequence
;------------------------------------

(defn rand-harmonize [x]
    (rand-nth (vector (+ x 7) (+ x 12) (+ x 4)))
  )

(defn playvec [item]
  (if (= java.lang.Long (type item))
j   (midi coll)
    (for [x coll]
      (midi x))))

(println  (playall cf))

;------------------------------------
; andrea's help
;------------------------------------

(defn schedule
  ([notes]
    (schedule 600 notes))
  ([incr notes]
    (map (fn [timing note]
          [timing note])
           (range 0 (* incr (count notes)) incr)
           notes)))

(for [item (schedule cf)]
      (flatten item))

(defn foo [[timing notes]]
  (if (= java.lang.Long (type notes))
    (list [timing notes])
    (for [x notes]
      [timing x])))

(println cf)
(schedule cf)


(defn playsched [[time note]]
     (ot/at (+ (ot/now) time)
            (tinn note)))

(defn playeverything [notes]
  (map playsched (apply concat (map foo (schedule notes)))))

(playeverything cf)

;------------------------------------

(defn playall
  ([notes]
    (playall 400 notes))
  ([inc notes]
    (map (fn [time note]
           (ot/at (+ (ot/now) time)
             (playvec note)))
         (range 0 (* inc (count notes)) inc)
         notes)))

(playall cf)

(def cf [62 65 [64 62] 67 65 69 67 65 64 62])
(def cf [60 64 62 60 65 64 67 65 64 62 60])
(def cf [60 67 72 75 63])
(def cf [60 61 62 62])

(type cf)

(defn playallfreq
  ([notes]
    (playallfreq 300 notes))
  ([inc notes]
    (map (fn [time note]
           (ot/at (+ (ot/now) time)
             (freq note)))
         (range 0 (* inc (count notes)) inc)
         notes)))

(type pythags)

(playallfreq pythags)
(playallfreq (equaltemp))
(ot/stop)

(defn play [last current]
  (hum last)
  ; (hum (harmonize last))
  current
)

(reduce play (conj cf 0))
(ot/at (+ (ot/now) 900) (hum 60))
