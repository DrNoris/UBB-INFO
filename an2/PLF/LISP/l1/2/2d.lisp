; d) Sa se scrie o functie care transforma o lista liniara intr-o multime.

(defun exista (e lista)
    (cond 
    ((null lista) nil)
    ((equal (car lista) e) t)
    (t(exista e (cdr lista))))
)

(defun multime (lista)
    (cond
    ((null lista) nil)
    ((exista (car lista) (cdr lista)) 
        (multime (cdr lista)))
    (t(cons (car lista) (multime (cdr lista))))))

(multime '(1 2 3 2 4 5 1 6 3))

