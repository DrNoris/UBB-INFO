; 9. Definiti o functie care substituie un element E prin elementele
;  unei liste L1 la toate nivelurile unei liste date L.
(defun subs(list e l1)
    (mapcar (lambda (x)
    (cond 
    ((equal x e) l1)
    ((listp x) (subs x e l1))
    (t x)
    )
    ) list)
)

(subs '(1 2 3 (4 5) 6) 3 '(a b c))  
; Rezultatul va fi (1 2 (a b c) (4 5) 6), adică 3 a fost înlocuit cu lista (a b c).

(subs '(1 2 (3 (4 5)) 6) 3 '(a b c))  
; Rezultatul va fi (1 2 ((a b c) (4 5)) 6), adică 3 a fost înlocuit cu (a b c) în sublista.

(subs '(1 2 3 4 5) 5 '(a b c))  
; Rezultatul va fi (1 2 3 4 (a b c)), adică 5 a fost înlocuit cu (a b c).
