(ns fux.demo
  (:require
      [overtone.live :as ot]
      [fux.parser :as p]
      [fux.player :as pl]))


(use 'clojure.pprint)

(pprint (p/parse-kern (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/146.krn")))


; play me!
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/159.krn")))

(ot/stop)


; debug me!
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/fugue1.krn")) 70)


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
