(ns fux.equal)

(defn lower [note]
  (if (< note 20.0)
    note
    (lower (/ note 2))))

(defn equaltemp [a acc]
  (if (> a 20000.0)
    acc
    (equaltemp (* a (Math/pow 2.0 (/ 1.0 12.0))) (cons a acc))))


(reverse (equaltemp (lower 440.0) (list)))
(count (reverse (equaltemp (lower 440.0) (list))))


(defn py [a acc]
  (if (> a 20000.0)
    acc
    (py (* a (/ 256.0 243.0)) (cons a acc))))

(reverse (py (lower 440.0) (list)))

