; a) Sa se elimine elementul de pe pozitia a n-a a unei liste liniare.

(defun elim (lista n)
    (cond
    ((null lista) nil)
    ((= 1 n) (cdr lista))
    (t (cons (car lista) (elim (cdr lista) (- n 1))))
    )
)

(elim '(1 2 3 4) 3)