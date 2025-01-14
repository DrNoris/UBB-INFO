; 10. Definiti o functie care determina numarul nodurilor de pe nivelul k
;  dintr-un arbore n-ar reprezentat sub forma (radacina lista_noduri_subarb1
;  ... lista_noduri_subarbn) Ex: arborelele este (a (b (c)) (d) (e (f))) si
; k=1 => 3 noduri 

(defun noduri (arb k)
    (cond 
    ((null arb) nil)
    ((= k 0) (list (car arb)))
    (t(mapcan (lambda (x) (noduri x (- k 1))) (cdr arb)))
    )
)

(length (noduri '(a (b (c)) (d) (e (f))) 1))  ; => 3 (nivelul 1 are nodurile b, d, e)
(length (noduri '(a (b (c)) (d) (e (f))) 2) ) ; => 2 (nivelul 2 are nodurile c și f)
(length (noduri '(a (b (c)) (d) (e (f))) 0))  ; => 1 (nivelul 0 are nodul a)
(length (noduri '(a (b (c)) (d) (e (f))) 3))  ; => 0 (nivelul 3 nu are nici un nod)
