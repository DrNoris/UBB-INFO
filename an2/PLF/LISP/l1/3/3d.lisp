; d) Sa se scrie o functie care intoarce intersectia a doua multimi.

;solutie facuta daca multimile sunt ordonate, imi e lene sa fac solutie generala
(defun intersectie (m1 m2)
    (cond 
    ((or (null m1) (null m2)) nil)
    ((= (car m1) (car m2)) (cons (car m2) (intersectie m1 (cdr m2))))
    ((< (car m1) (car m2)) (intersectie (cdr m1) m2))
    (t(intersectie m1 (cdr m2)))
    )
)

(intersectie '(1 2 3 4 5) '(3 4 5 6 7))
