; 13. Definiti o functie care substituie un element prin altul la toate
;  nivelurile unei liste date.
(defun substituire(list e sub)
    (mapcar (lambda (x)
    (cond
    ((equal x e) sub)
    ((listp x) (substituire x e sub))
    (t x)
    ))list)
)

(substituire '(A B (C (D (A (A))))) 'A 'X)