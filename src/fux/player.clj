(ns fux.player
  (:require
          [overtone.live :as ot]
          [fux.tuners :as tuner]))


(defn stop [] 
  (ot/stop))

(ot/definst sin-wave  [freq 0 attack 0 sustain 0 release 0 vol 0.6]
  (* (ot/env-gen (ot/lin attack sustain release) 1 1 0 1 ot/FREE)
     (ot/sin-osc freq)
     vol))

; now i need a function that, given a metronome marking and an amount of note offset (quarter note = 1) defines an absolute time

(defn schedule-note [offset, mm]
    (* offset (float (/ 1000 (/ mm 60)))))

(defn playsched [note offset start duration]
     (ot/at (+ start offset)
            (sin-wave ((tuner/equaltemp) note)
                      0
                      duration
                      0
                      0.1)))

; now I just need to apply that to all the notes in a spine.
(defn schedule-spine [spine now mm]
  (map #(playsched (% :notecode) (schedule-note (% :offset) mm) now (* (/ 60 mm) (% :duration))) spine))

;this one!!!
(defn play-kern
  ([kern] (play-kern kern 100))
  ([kern mm]
  (map #(schedule-spine % (ot/now) mm) (kern :spines))))
