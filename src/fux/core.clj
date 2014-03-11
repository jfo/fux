(ns fux.core
  (:require
            [fux.hdparser :as p]
            [fux.tuners :as tuner]
            [fux.player :as player])
)


(+ 1 1)

(println p/kern)
(p/noter p/kern)
(p/numberer (p/noter p/kern))

(player/playeverything (vec (for [x (p/numberer (p/noter p/kern))] (vec x))))

