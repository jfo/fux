(ns fux.tuners)


(defn lower [note]
    (if (< note 20.0)
          note
          (lower (/ note 2))))
;------------------------------------
;     equal temperment tuning       |
;------------------------------------

(defn equaltemp
    ([] (equaltemp (lower 440.0) (vector)))
    ([a] (equaltemp (lower a) (vector)))
    ([a acc]
       (if (> a 20000.0)
             (vec(reverse acc))
             (equaltemp (* a (Math/pow 2.0 (/ 1.0 12.0))) (cons a acc)))))

(def equaltemps (equaltemp))

;------------------------------------
;       pythagorean tuning          |
;------------------------------------


(defn pythagorean-up
    ([] (pythagorean-up 440.0 (vector)))
    ([a] (pythagorean-up a (vector)))
    ([a acc]
       (if (< 5 (count acc))
             (reverse acc)
               (pythagorean-up (* 3/2 a) (cons a acc)))))

(defn pythagorean-down
    ([] (pythagorean-down 440.0 (vector)))
    ([a] (pythagorean-down a (vector)))
    ([a acc]
       (if (< 6 (count acc))
             (reverse (rest (reverse acc)))
               (pythagorean-down (* 2/3 a) (cons a acc)))))

(defn pythagorean
    ([] (pythagorean 440.0 (vector)))
    ([a] (pythagorean a (vector)))
    ([a acc]
         (flatten (cons (pythagorean-down a) (cons (pythagorean-up a) acc)))))

(defn expand
    ([start] (expand (lower start) (vector)))
    ([start acc]
         (if (< start 20000)
                 (expand (* 2 start) (cons start acc))
                 (reverse acc))))


(def pythags (vec (drop 5 (vec (flatten (vector (sort (flatten (map expand (pythagorean))))))))))
