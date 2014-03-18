(ns fux.core
  (:require
      [fux.parser :as p]
      [fux.player :as pl]))

(use 'clojure.pprint)


; play me!
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/095.krn")) 100)


(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f04.krn")) 200)


(pprint (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f04.krn")))

(pprint (p/parse-kern (p/prep-kern "/Users/jeff/code/fux/resources/haydn/op01n2-01.krn")))
(pprint (p/parse-kern (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f06.krn")))

(pl/stop)


; debug me!
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/fugue1.krn")) 70)


(def chorale
 (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f06.krn"))
(pprint chorale)

(p/extract-comments chorale)
(p/strip-comments chorale)
(pprint (p/split-spines chorale))
(pprint (p/extract-spines chorale))

(def test-spine (first (p/extract-spines chorale)))

(pprint test-spine)

(p/extract-spine-comments test-spine)
(p/strip-spine-comments test-spine)

(p/remove-null (p/strip-spine-comments test-spine))

(def tokens (p/remove-measure (p/strip-spine-comments test-spine)))
(pprint tokens)

(map pprint tokens)
(map p/note tokens)
(map p/duration tokens)

(p/duration (nth tokens 1))

(map
(fn [token] read-string
  (clojure.string/join
    (re-seq #"[0-9.]"
            (first (clojure.string/split
                     token #"/s")))))
  tokens)

(p/duration (first tokens))
(p/spine-noter tokens)
(p/add-offset (p/spine-noter tokens))





; nothing to see here...
; =====================
; (pl/play-kern (p/parse-kern
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
; (def test-spine (first ((p/parse-kern kern) :spines)))
; (schedule-spine test-spine)
; (def test-note (nth (first ((p/parse-kern kern) :spines)) 5))
; (schedule-note 5.0)
