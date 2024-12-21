;; d) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
;; dintr-o lista liniara.


#||
                              {nr1, nr1 = nr2
cmmdc(nr1: numar, nr2: numar)={cmmdc(nr1-nr2, nr2), nr1 > nr2
                              {cmmdc(nr1, nr2-nr1), nr2 > nr1
||#
(defun cmmdc (nr1 nr2)
    (cond

    ((= nr1 nr2) nr1)

    ((< nr1 nr2)
        (cmmdc nr1 (- nr2 nr1)))

    ((> nr1 nr2)
        (cmmdc (- nr1 nr2) nr2))))

#||
                        {l1 (+) filtrare(l2..ln: lista), l1 = numar 
filtrare(l1..ln: lista)={filtrare(l2..ln), l1 = lista
                        {[], n = 1 
||#
(defun filtrare (lista)
  (cond
    ((null lista) nil) 
    ((numberp (car lista)) ;
     (cons (car lista) (filtrare (cdr lista)))) 
    (t (filtrare (cdr lista)))))

#||
                           {cmmdc(l1, cmmdc_lista(l2..ln)), n > 2
cmmdc_lista(l1..ln: lista)={l1, n = 1 
                           {0, n = 0
                           
||#

(defun cmmdc_lista (lista)
  (cond
    ((null lista) 0)

    ((null (cdr lista)) (car lista)) 
    
    (t (cmmdc (car lista) (cmmdc_lista (cdr lista))))))

(cmmdc_lista (filtrare '(12 15 30)))
(cmmdc_lista (filtrare '(12 "a" 30)))
(cmmdc_lista (filtrare '(10 nil "abc" nil 100))) 
(cmmdc_lista (filtrare '(12 15 30)))
(cmmdc_lista (filtrare '(7 14 28))) 
(cmmdc_lista (filtrare '(10 100 1000)))