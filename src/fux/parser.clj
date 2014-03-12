(ns fux.parser
  (:require [clojure.java.io :as io]))

(def kern
  (with-open [rdr (io/reader "/Users/jeff/code/fux/resources/bach-chorales/017.krn")]
    (->> (line-seq rdr)
         (into [] ))))


; ==============
; comments
; ==============
(defn extract-comments [kern]
  (remove #(not= \! (first %)) kern))

; ==============
; spines
; ==============

(defn strip-comments [kern]
  (let [notes (remove #(= \! (first %)) kern)]
    notes))
(defn split-spines [kern]
  (let [vectors (strip-comments kern)]
    (map #(clojure.string/split % #"\t") vectors)))

(defn tokenize-spine [spines]
    (for [ i spines ]
      (first i)))

(defn extract-spines [spines]
  (loop [spines (split-spines spines)
         acc (vector)]
      (if (empty? (flatten spines))
        acc
        ; (conj (tokenize-spine spines) acc))))
        (recur (map rest spines) (conj acc (tokenize-spine spines))))))

(extract-spines kern)


(defn map-spines [kern]
  {:comments (extract-comments kern) :spines (extract-spines kern)})


(doseq [i (map-spines kern)]
  (println i))


(doseq [i kern]
  (println i))

(print kern)

(extract-comments kern)
(strip-comments kern)
(tokenize-spine kern)
(extract-spines kern)


