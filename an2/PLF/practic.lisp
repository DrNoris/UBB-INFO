;                        {nil, n = 0
;liniar(l1..ln: lista) = {l1 (+) liniar(l2..ln), l1: numar
;                        {liniar(l1) (+) liniar (l2..ln), l1: lista
;                        {liniar(l2..ln), l1:atom nenumeric (altfel)
;returneaza o lista liniara cu numere

(defun liniar(lista)
    (cond
    ((null lista) nil)
    ((listp (car lista)) (append (liniar (car lista)) (liniar (cdr lista))))
    ((numberp (car lista)) (append (list(car lista)) (liniar (cdr lista))))
    (t(liniar (cdr lista)))
    )
)
;                                  {treu, poz = 2 & n = 0
; munte(l1..l2: lista, poz: numar)={munte(l2..ln, 1), poz = 1 & l1 < l2
;                                  {munte(l2..ln, 1), poz = 0 & l1 < l2
;                                  {munte(l2..ln, 2), poz = 1 & l1 > l2
;                                  {munte(l2..ln, 2), poz = 2 & l1 > l2
;                                  {false, altfel
;verifica daca o lista liniara de numre este sub forma de munte

(defun munte (lista poz)
    (cond
    ((and (= poz 2) (null (cadr lista))) t)
    ((and (= poz 0) (< (car lista) (cadr lista))) (munte (cdr lista) 1))
    ((and (= poz 1) (< (car lista) (cadr lista))) (munte (cdr lista) 1))
    ((and (= poz 1) (> (car lista) (cadr lista))) (munte (cdr lista) 2))
    ((and (= poz 2) (> (car lista) (cadr lista))) (munte (cdr lista) 2))
    (t(print "Nu este munte"))
))


;main(lista: list) = {munte(liniar(lista), 0)
;functia principala 

(defun main (lista)
    (munte (liniar lista) 0)
)


(main '(1 (A B (5) (6 (7 J)) (9) 4) K))
(main '(3 78 90 3))
(main '(5 3 2))
(main '(1 10 (A B (5) (6 (7 (J))) (9) 4) 2 K))
