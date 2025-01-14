; 5. Sa se intoarca adancimea la care se afla un nod intr-un arbore de tipul (1).

(defun cautare (arbore nod adancime)
    (cond
    ((null arbore) nil)
    ((equal (car arbore) nod) adancime)
    (t(or (cautare(cadr arbore) nod (+ 1 adancime))
        (cautare(caddr arbore) nod (+ 1 adancime))))
    )
)

(setq arbore '(A (B) (C (D) (E)))) 
(cautare arbore 'D 0)