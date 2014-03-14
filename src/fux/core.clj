(ns fux.core
  (:require
            [overtone.live :as ot]
            [fux.parser :as p]
            ; [fux.scheduler :as sched]
            ; [fux.player :as play]
            [fux.spinetokenparser :as s]
            [fux.tuners :as tuner]
            [clojure.java.io :as io]))

(use 'clojure.pprint)

(def kern
  (with-open [rdr (io/reader "/Users/jeff/code/fux/resources/bach-chorales/017.krn")]
    (->> (line-seq rdr)
         (into [] ))))

(print kern)

(doseq [i kern]
  (println i))

(p/extract-comments kern)
(p/strip-comments kern)
(p/split-spines kern)
(p/tokenize-spine (p/split-spines kern))

(pprint (p/parse-kern kern))

; now i need a function that, given a metronome marking and an amount of note offset (quarter note = 1) defines an absolute time
(defn schedule-note
  ([offset] (schedule-note offset 100))
  ([offset, mm]
    (* offset (float (/ 1000 (/ 100 60))))))

; plays note immediately
(ot/definst midi [note 60 amp 0.3]
   (let [freq (ot/midicps note)]
      (* amp
          (ot/env-gen (ot/perc 0.1 2) 10 1 0 1 :action ot/FREE)
          (+ (ot/sin-osc (/ freq 1))))))

(defn playsched [note offset]
     (ot/at (+ (ot/now) offset)
            (midi note)))

; now I just need to apply that to all the notes in a spine.
(defn schedule-spine [spine]
  (map #(playsched (% :notecode) (schedule-note (% :offset))) spine))


(pprint test-spine)

(test-note :offset)
(playsched (test-note :notecode)(schedule-note (test-note :offset)))


;this one!!!
(map schedule-spine ((p/parse-kern kern) :spines))
;this one!!!

(schedule-spine test-spine)

(def test-spine (first ((p/parse-kern kern) :spines)))
(def test-note (nth (first ((p/parse-kern kern) :spines)) 5))

(midi)
(schedule-note 5.0)
(playsched 60 1000)








