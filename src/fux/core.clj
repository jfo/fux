(ns fux.core
  (:require
            [overtone.live :as ot]
            [fux.parser :as p]
            [fux.scheduler :as sched]
            [fux.player :as play]
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


(pprint (map #(add-offset (% :notes)) (map p/chunk-spine (p/extract-spines kern))))
(pprint (p/add-offset (map p/chunk-spine (p/extract-spines kern))))


(remove #(= "." %) (flatten (p/extract-spines kern)))




(pprint (p/parse-kern kern))

(def parsed (p/parse-kern kern))


(def test-spine (first (parsed :spines)))
(pprint test-spine)

(defn schedule-spine
  ([spine] (schedule-spine spine 100))
  ([spine mm]
   (map #((ot/at (+ (ot/now) 1000) (play/midi (% :notecode)))) spine)))


(schedule-spine test-spine)








