; a) Definiti o functie care intoarce produsul a doi vectori.
(defun produs_vectori (v1 v2)
    (cond 
    ((or (null v1) (null v2)) 0)
    (t (+ (* (car v1) (car v2)) (produs_vectori (cdr v1) (cdr v2))))))

(produs_vectori '(1 2 3) '(4 5 6))
