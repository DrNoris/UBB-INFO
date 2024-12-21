;; b) Definiti o functie care substituie un element E prin elementele unei liste
;; L1 la toate nivelurile unei liste date L.

#||
                                                 {nil, n = 0
inlocuire(l1..ln: lista, k1..kn: lista, e: elem)={k1..kn (+) inlocuire(l2..ln, k1..kn, e), l1 = e
                                                 {l1 (+) inlocuire(l2..ln, k1..kn, e), altfel
||#

(defun inlocuire (lista lista1 e)
    (cond 
        ((null lista) nil)

        ((eql (car lista) e)
            (append lista1 (inlocuire (cdr lista) lista1 e)))
            
        ((listp (car lista))
            (cons (inlocuire (car lista) lista1 e) (inlocuire (cdr lista) lista1 e)))

        (t (cons (car lista) (inlocuire (cdr lista) lista1 e)))))


(inlocuire '(1 (2 3) (4 (2))) '(10 20) 2)
