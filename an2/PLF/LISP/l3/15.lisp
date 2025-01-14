; 15. Sa se construiasca o functie care intoarce numarul atomilor dintr-o
;  lista, de la orice nivel.

(defun atomi(list)
    (mapcan (lambda (x) 
    (cond
    ((null x) (list 0))
    ((atom x) (list 1))
    (t (atomi x))
    )
    ) list)
)

(apply #'+ (atomi '(a (b (c)) (d) (e (f)))))