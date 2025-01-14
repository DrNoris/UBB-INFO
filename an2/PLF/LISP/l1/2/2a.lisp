; a) Definiti o functie care selecteaza al n-lea element al unei liste, sau
; NIL, daca nu exista.

(defun selectie (n lista)
    (cond
    ((= n 1) (car lista))
    ((null lista) nil)
    (t (selectie (- n 1) (cdr lista)))))

(selectie 3 '(4 3 2 1))