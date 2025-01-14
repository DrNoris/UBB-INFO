; b) Sa se construiasca o functie care intoarce adancimea unei liste.
(defun adancime (lista)
  (cond
    ((null lista) 1) 
    ((listp (car lista))
     (max (+ 1 (adancime (car lista))) (adancime (cdr lista))))
    (t (adancime (cdr lista)))))


(adancime '((1 2) (3 4) (5 6)))  ; 2
(adancime '((1 2) 3 4))  ; 2
(adancime '(1 2 3))  ; 1
(adancime '())  ; 0
(adancime '((1 (2 3)) 4))  ; 3