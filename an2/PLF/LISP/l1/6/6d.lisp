; d) Sa se construiasca o functie care intoarce numarul atomilor dintr-o lista,
; de la nivel superficial.

(defun nratomi (lista)
    (cond
    ((null lista) 0)
    ((atom (car lista)) (+ 1 (nratomi (cdr lista))))
    (t(nratomi (cdr lista)))
    )
)

(nratomi '(A 1 B (2 (F ()) G F) 0))