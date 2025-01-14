; 8. Sa se construiasca lista nodurilor unui arbore de tipul (2) parcurs in
;  inordine.

(defun inordine (arbore)
    (cond
    ((null arbore) nil)
    (t(append (inordine (cadr arbore)) (list (car arbore)) (inordine (caddr arbore))))))

(setq arbore '(A (B) (C (D) (E)))) 
(inordine arbore)