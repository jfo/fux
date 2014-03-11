(ns fux.player
  (:require [overtone.live :as ot]
            [fux.hdparser :as p]
            [fux.tuners :as tuners]))

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
(ot/definst midi [note 60 amp 0.3]
   (let [freq (ot/midicps note)]
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 10 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1)))
 )))

; takes a note value and maps it to my frequency list to get frequency,
; then sends that along to the freq instrument that has been defined

(defn tinn [note]
  (freq (tuners/equaltemps note )))

;------------------------------------------------------------------------
;   accepts vec of vecs. subvec spec: [time scheduled, note names at that time]
;------------------------------------------------------------------------

(defn schedule
  ([notes]
    (schedule 600 notes))
  ([incr notes]
    (map (fn [timing note]
          [timing note])
           (range 0 (* incr (count notes)) incr)
           notes)))

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

