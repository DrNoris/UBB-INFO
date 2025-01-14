; 14. Definiti o functie care da adancimea unui arbore n-ar reprezentat sub forma
;  (radacina lista_noduri_subarb1...lista_noduri_subarbn)
;  Ex: adancimea arborelui este (a (b (c)) (d) (e (f))) este 3

(defun adancime (arb)
    (cond
    ((null (cdr arb)) 1)
    (t (+ 1 (apply #'max (mapcar #'adancime (cdr arb))))))) 
    
(adancime '(a (b (c)) (d) (e (f))))