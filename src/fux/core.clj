(ns fux.core
  (:require
      [fux.parser :as p]
      [fux.player :as pl]))

(defn -main []
  (pl/play-kern (p/parse-kern
    (p/prep-kern "/Users/jeff/code/fux/resources/bach-chorales/095.krn"))))
