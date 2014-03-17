(ns fux.player
  (:require
            [overtone.live :as ot]
            [fux.tuners :as tuner]))

(ot/definst sin-wave  [freq 440 attack 0.1 sustain 0.1 release 0.3 vol 0.1]
  (* (ot/env-gen (ot/lin attack sustain release) 0.1 1 0 1 ot/FREE)
     (ot/sin-osc freq)
     vol))

(ot/definst square-wave [freq 440 attack 0 sustain 0.2 release 0 vol 0.2]
  (* (ot/env-gen (ot/lin attack sustain release) 1 1 0 1 ot/FREE)
     (ot/lf-pulse:ar freq)
     vol))

; now i need a function that, given a metronome marking and an amount of note offset (quarter note = 1) defines an absolute time

(defn schedule-note
  ([offset] (schedule-note offset 100))
  ([offset, mm]
    (* offset (float (/ 1000 (/ mm 60))))))

(defn playsched [note offset start duration]
     (ot/at (+ start offset)
            (sin-wave ((tuner/equaltemp) note) 0.1 duration)))

; now I just need to apply that to all the notes in a spine.
(defn schedule-spine [spine now]
  (map #(playsched (% :notecode) (schedule-note (% :offset)) now (* (/ 60 130) (% :duration))) spine))

;this one!!!
(defn play-kern [kern]
  (map #(schedule-spine % (ot/now)) (kern :spines)))
