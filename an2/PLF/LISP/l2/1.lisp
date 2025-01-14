; 1. Se da un arbore de tipul (1). Sa se afiseze calea de la radacina pana la un
;  nod x dat.

(defun cautare(arbore nod)
    (cond
    ((null arbore) nil)
    ((equal (car arbore) nod) (list (car arbore)))
    (t(if (or (cautare (cadr arbore) nod) (cautare (caddr arbore) nod))
        (cons (car arbore) (or (cautare (cadr arbore) nod) (cautare (caddr arbore) nod)))
        ))))

(setq arbore '(A (B) (C (D) (E))))
(cautare arbore 'D) 
(cautare arbore 'B) 
(cautare arbore 'E)