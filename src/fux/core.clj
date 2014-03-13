(ns fux.core
  (:require
            [fux.parser :as p]
            [fux.scheduler :as sched]
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

(pprint (p/extract-spines kern))
(pprint (map p/chunk-spine (p/extract-spines kern)))


(remove #(= "." %) (flatten (p/extract-spines kern)))
(p/extract-spines kern)

(p/parse-kern kern)

(def parsed (p/parse-kern kern))

(pprint parsed)

(def test-spine
  (remove #(= \= (first %))
  ((first (parsed :spines)) :notes)))

(pprint test-spine)

(defn add-offset [spine]
  (rest (reverse
    (reduce (fn [acc note]
       (conj acc
         (assoc note
            :offset (+ (/ 1 (read-string (note :duration)))
                       ((first acc) :offset)))))
        '({:offset 0 :duration 0})
        spine))))

(read-string "0")

(pprint (add-offset test-spine))



(reduce (fn [acc x]
          (conj acc (+ x (last acc))))
        [1]
        [2 3 4 5])

(reduce + [0 1])


(reduce #(% :duration) test-spine)

(map (fn[m] {:notecode (m :notecode)}) test-spine)


(schedule test-spine 100)



