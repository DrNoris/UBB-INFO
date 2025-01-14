; 3. Se da un arbore de tipul (1). Sa se precizeze numarul de niveluri din
;  arbore.

(defun niveluri (arbore)
    (cond
    ((atom arbore) 0)
    (t(max (+ 1 (niveluri (cadr arbore))) (+ 1 (niveluri (caddr arbore)))))
    )
)

(setq arbore '(A (B) (C (D) (E)))) 
(niveluri arbore)