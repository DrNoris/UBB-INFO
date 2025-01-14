;;a) Definiti o functie care interclaseaza cu pastrarea dublurilor doua liste
;;liniare sortate.

#||
                                           {l1..ln, k = 0
interclasare(l1..ln: lista, k1..kn: lista)={k1..kn, n = 0
                                           {l1 (+) interclasare(l2..ln, k1..kn), l1 < k1
                                           {k1 (+) interclasare(l1..ln, k2..kn), l1 >= k1
||#


(defun interclasare (lista1 lista2)
  (cond 
    ((null lista1) lista2)
    ((null lista2) lista1)
    ((< (car lista1) (car lista2))
      (cons (car lista1) (interclasare (cdr lista1) lista2)))
    ((> (car lista1) (car lista2))
      (cons (car lista2) (interclasare lista1 (cdr lista2))))
    (t (cons (car lista1)
             (cons (car lista2)
                   (interclasare (cdr lista1) (cdr lista2)))))))

(interclasare '(1 3 5 7) '(1 3 5 7))
