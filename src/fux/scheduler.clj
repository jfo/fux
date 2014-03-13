(ns fux.scheduler)

(defn schedule
  ([notes]
    (schedule 600 notes))
  ([incr notes]
    (map (fn [timing note]
          [timing note])
           (range 0 (* incr (count notes)) incr)
           notes)))

(defn foo [[timing notes]]
  (if (= java.lang.Long (type notes))
    (list [timing notes])
    (for [x notes]
      [timing x])))

(println cf)
(schedule cf)


(defn playsched [[time note]]
     (ot/at (+ (ot/now) time)
            (tinn note)))

(defn playeverything [notes]
  (map playsched (apply concat (map foo (schedule notes)))))

(playeverything cf)
