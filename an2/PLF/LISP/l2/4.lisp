; 4. Sa se converteasca un arbore de tipul (2) la un arbore de tipul (1).

(defun conversie (arbore)
    (cond
    ((null arbore) nil)
    ((atom arbore) (list arbore 0))
    (t(append 
        (list(car arbore)) 
        (list (length (cdr arbore))) 
        (conversie (cadr arbore)) 
        (conversie (caddr arbore))))
    )
)

(setq arbore '(A (B) (C (D) (E)))) 
(conversie arbore) 
