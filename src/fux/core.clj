(ns fux.core
  (:require
            [overtone.live :as ot]
            [fux.parser :as p]
            ; [fux.scheduler :as sched]
            ; [fux.player :as play]
            ; [fux.spinetokenparser :as s]
            [fux.tuners :as tuner]
            [clojure.java.io :as io]))

(use 'clojure.pprint)

(def kern
  (with-open [rdr (io/reader "/Users/jeff/code/fux/resources/bach-chorales/200.krn")]
    (->> (line-seq rdr)
         (into [] ))))

; (print kern)
; (doseq [i kern]
;   (println i))
; (p/extract-comments kern)
; (p/strip-comments kern)
; (p/split-spines kern)
; (p/tokenize-spine (p/split-spines kern))
; (pprint (p/parse-kern kern))
; (pprint tuner/pythags)
; (pprint (tuner/equaltemp))


(ot/definst sin-wave [freq 440 attack 0.5 sustain 0.2 release 1.1 vol 0.1]
  (* (ot/env-gen (ot/lin attack sustain release) 1 1 0 1 ot/FREE)
     (ot/sin-osc freq)
     vol))

(ot/definst square-wave [freq 440 attack 0.01 sustain 0.4 release 0.1 vol 0.4]
  (* (ot/env-gen (ot/lin attack sustain release) 1 1 0 1 ot/FREE)
     (ot/lf-pulse:ar freq)
     vol))

; now i need a function that, given a metronome marking and an amount of note offset (quarter note = 1) defines an absolute time
(defn schedule-note
  ([offset] (schedule-note offset 100))
  ([offset, mm]
    (* offset (float (/ 1000 (/ mm 60))))))


(defn playsched [note offset start]
     (ot/at (+ start offset)
            (sin-wave ((tuner/equaltemp) note))))

; now I just need to apply that to all the notes in a spine.
(defn schedule-spine [spine now]
  (map #(playsched (% :notecode) (schedule-note (% :offset)) now) spine))

; (def test-spine (first ((p/parse-kern kern) :spines)))
; (schedule-spine test-spine)
; (def test-note (nth (first ((p/parse-kern kern) :spines)) 5))
; (schedule-note 5.0)

;this one!!!
(defn play-kern [kern]
  (map #(schedule-spine % (ot/now)) (kern :spines)))

(play-kern (p/parse-kern kern))
;this one!!!





