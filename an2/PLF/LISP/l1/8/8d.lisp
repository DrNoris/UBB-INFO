; d) Sa se scrie o functie care testeaza daca o lista liniara este o multime.

(defun exista (lista e)
    (cond
    ((null lista) t)
    ((= (car lista) e) nil)
    (t(exista (cdr lista) e))
    )
)

(defun test (lista)
    (cond
        ((null lista) "Este multime")
        ((not (exista (cdr lista) (car lista))) "Nu este multime")
        (t(test (cdr lista)))
    )
)

(test '(1 2 3 4 5))
(test '(1 2 5 4 5))