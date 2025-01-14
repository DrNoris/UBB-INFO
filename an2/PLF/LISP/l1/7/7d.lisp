; d) Definiti o functie care interclaseaza fara pastrarea dublurilor doua liste
; liniare sortate.

(defun interclasare (m1 m2)
    (cond
    ((or (null m1) (null m2)) nil)
    ((null m1) m2)
    ((null m2) m1)
    ((< (car m1) (car m2)) (cons (car m1) (interclasare (cdr m1) m2)))
    ((< (car m2) (car m1)) (cons (car m2) (interclasare m1 (cdr m2))))
    (t(cons (car m2) (interclasare (cdr m1) (cdr m2))))))

(interclasare '(2 6 10) '(1 5 9 10))