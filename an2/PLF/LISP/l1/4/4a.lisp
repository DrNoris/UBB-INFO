; a) Definiti o functie care intoarce suma a doi vectori.

(defun suma_vectori(v1 v2)
    (cond
    ((or (null v1) (null v2)) nil)
    (t(cons (+ (car v1) (car v2)) (suma_vectori (cdr v1) (cdr v2))))
    )
)

(suma_vectori '(1 2 3) '(4 5 6))
