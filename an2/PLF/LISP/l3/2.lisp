; 2. Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
;  care apar, pe orice nivel, dar in aceeasi ordine. De exemplu
;  (((A B) C) (D E)) --> (A B C D E)
(defun construire(l)
    (cond
    ((null l) nil)
    ((atom l) (list l))
    (t(apply #'append (mapcar #'construire l)))
    )
)

(construire '()) ;; NIL
(construire '(a b c)) ;; (A B C)
(construire '(((a b) c) (d e))) ;; (A B C D E)
(construire '((1 (2 3)) (4 (5 (6))))) ;; (1 2 3 4 5 6)
(construire '(x (y) z (((w))))) ;; (X Y Z W)

