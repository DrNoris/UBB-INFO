; 7. Sa se scrie o functie care calculeaza suma numerelor pare minus suma
;  numerelor impare la toate nivelurile unei liste.

(defun calcul (lista)
    (- (apply #'+ (mapcar (lambda (x) 
        (cond
        ((and (numberp x) (evenp x)) x)
        ((listp x) (calcul x))
        (t 0)
        )
    ) lista))
        (apply #'+ (mapcar (lambda (x) 
        (cond
        ((and (numberp x) (not (evenp x))) x)
        ((listp x) (calcul x))
        (t 0)
        )
    ) lista)))
)

(calcul '(1 2 3 4))