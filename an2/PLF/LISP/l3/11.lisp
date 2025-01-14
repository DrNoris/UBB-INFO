; 11. Sa se scrie o functie care sterge toate aparitiile unui atom de la
;  toate nivelurile unei liste.
(defun sterge (list e)
    (mapcan (lambda (x) 
    (cond 
    ((equal x e) nil)
    ((listp x) (list (sterge x e)))
    (t (list x))
    )) list)
)

(sterge '(a (b c) (a d) e (a)) 'a)
