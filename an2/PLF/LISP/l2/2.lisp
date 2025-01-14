;  Sa se tipareasca lista nodurilor de pe nivelul k dintr-un arbore de tipul
;  (1).

(defun tiparire (arbore k)
    (cond
    ((null arbore) nil)
    ((= 0 k) (list (car arbore)))
    (t(append (tiparire (cadr arbore) (- k 1)) (tiparire (caddr arbore) (- k 1))))
    )
)

(setq arbore '(A (B) (C (D) (E)))) 
(tiparire arbore 0) 
(tiparire arbore 1)
(tiparire arbore 2)
(tiparire arbore 3)