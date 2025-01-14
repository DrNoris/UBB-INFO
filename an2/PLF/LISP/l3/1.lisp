; 1. Sa se construiasca o functie care intoarce adancimea unei liste.
(defun adancime (l)
    (cond
    ((null l) 0)
    ((atom l) 0)
    (t(+ 1 (reduce #'max (mapcar #'adancime l))))
))

(adancime '())
(adancime '(1 2 3))
(adancime '(1 (2) (3 (4))))
(adancime '((1 2) (3 (4 5 (6)))))
(adancime '(1 (2 (3 (4 (5))))))
