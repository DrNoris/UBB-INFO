; 6. Sa se construiasca o functie care intoarce produsul atomilor numerici
;  dintr-o lista, de la orice nivel.
(defun produs (lista)
    (apply #'* (mapcar (lambda (x)
        (cond
        ((numberp x) x)
        ((listp x) (produs x))
        (t 1)
        )
    ) lista))
)

(produs '(1 2 2 A (B (4))))