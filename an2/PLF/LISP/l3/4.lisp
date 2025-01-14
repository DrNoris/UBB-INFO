; 4. Sa se construiasca o functie care intoarce suma atomilor numerici
;  dintr-o lista, de la orice nivel.
(defun suma (list)
    (apply #'+ (mapcar (lambda (x)
        (cond
        ((listp x) (suma x))
        ((numberp x) x)
        (t 0))) list)))

(suma '(1 2 3))
(suma '(10 A (3)))
(suma '(A (B(10 (A (1)))) (C)))
