(ns fux.core
  (:require
      [overtone.live :as ot]
      [fux.parser :as p]
      [fux.tuners :as t]
      [fux.player :as pl]))

(use 'clojure.pprint)

(def kern (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/095.krn"))

(print kern)

(doseq [i kern]
  (println i))


; =====================================
;  ____   _    ____  ____  _____ ____
; |  _ \ / \  |  _ \/ ___|| ____|  _ \
; | |_) / _ \ | |_) \___ \|  _| | |_) |
; |  __/ ___ \|  _ < ___) | |___|  _ <
; |_| /_/   \_\_| \_\____/|_____|_| \_\
; =====================================


(pprint (p/extract-comments kern))
(pprint (p/strip-comments kern))
(pprint (p/split-spines kern))

(pprint (p/tokenize-spine (p/split-spines kern)))

(pprint (p/extract-spines kern))

(def test-spine (first (p/extract-spines kern)))


(pprint test-spine)
(pprint (p/extract-spine-comments test-spine))
(pprint (p/strip-spine-comments test-spine))
(pprint (p/remove-null (p/strip-spine-comments test-spine)))
(pprint (p/remove-measure (p/remove-null (p/strip-spine-comments test-spine))))

(def notes (p/remove-measure (p/remove-null (p/strip-spine-comments test-spine))))
(pprint notes)

(def token (nth notes 5))
(print token)

(p/note token)
(p/duration token)

(pprint (p/spine-noter notes))
(pprint (p/add-offset (p/spine-noter notes)))
(pprint (p/chunk-spine test-spine))
(pprint (p/parse-kern kern))


; ================================
; _____ _   _ _   _ _____ ____
; |_   _| | | | \ | | ____|  _ \
;   | | | | | |  \| |  _| | |_) |
;   | | | |_| | |\  | |___|  _ <
;   |_|  \___/|_| \_|_____|_| \_\
; ================================


(pprint t/pythags)
(pprint t/equaltemps)

(t/equaltemps 60)
(t/pythags 60)

(t/equaltemps 90)
(t/pythags 90)

(t/equaltemps 30)
(t/pythags 30)


; ====================================
;  ____  _        _ __   _______ ____
; |  _ \| |      / \\ \ / / ____|  _ \
; | |_) | |     / _ \\ V /|  _| | |_) |
; |  __/| |___ / ___ \| | | |___|  _ <
; |_|   |_____/_/   \_\_| |_____|_| \_\
; ====================================


(pl/tuning-note)

(pl/schedule-note 0 60)
(pl/schedule-note 10 60)
(pl/schedule-note 5 165)

(pl/playsched 60 0 (ot/now) 1)
(pl/playsched 62 0 (+ (ot/now) 1000) 1)

((fn []
  (pl/playsched 60 0 (ot/now) 1)
  (pl/playsched 62 0 (+ (ot/now) 1000) 1)
  (pl/playsched 64 0 (+ (ot/now) 2000) 1)
  (pl/playsched 65 0 (+ (ot/now) 3000) 1)
  (pl/playsched 67 0 (+ (ot/now) 4000) 1)))

((fn []
  (pl/playsched 60 0 (ot/now) 1)
  (pl/playsched 64 0 (ot/now) 1)
  (pl/playsched 62 0 (+ (ot/now) 1000) 1)
  (pl/playsched 65 0 (+ (ot/now) 1000) 1)
  (pl/playsched 64 0 (+ (ot/now) 2000) 1)
  (pl/playsched 67 0 (+ (ot/now) 2000) 1)
  (pl/playsched 65 0 (+ (ot/now) 3000) 1)
  (pl/playsched 69 0 (+ (ot/now) 3000) 1)
  (pl/playsched 67 0 (+ (ot/now) 4000) 1)
  (pl/playsched 72 0 (+ (ot/now) 4000) 1)))

; astute viewers may note a slight rolling time problem above,
; simple to fix by passing in time once at the top of the parsing function!


(pl/stop)

; play me!

(pprint (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/095.krn")))
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/095.krn")))


(pprint (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f02.krn")) 100)
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f02.krn")) 100)


(pprint (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f04.krn")))
(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/wtc1f04.krn")) 500)




(pl/play-kern (p/parse-kern
  (p/prep-kern "/Users/jeff/code/fux/resources/mysterium.krn")) 100)
