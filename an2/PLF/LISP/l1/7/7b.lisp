; b) Definiti o functie care substituie prima aparitie a unui element intr-o
; lista data.

(defun substitutie (lista e subs)
    (cond 
    ((null lista) nil)
    ((= e (car lista)) (cons subs (cdr lista)))
    (t(cons (car lista) (substitutie (cdr lista) e subs)))
    )
)

(substitutie '(1 2 100 4 5) 100 3)