; d) Sa se construiasca o functie care intoarce maximul atomilor numerici
; dintr-o lista, de la nivelul superficial.

(defun maxim (lista e)
    (cond
    ((null lista) e)
    ((listp (car lista)) (maxim (cdr lista) e))
    ((numberp (car lista))
    (maxim (cdr lista) (if (or (equal e nil) (> (car lista) e)) (car lista) e)))
    (t (maxim (cdr lista) e)))) 

(defun main (lista)
    (maxim lista nil)
)

(main '(3 a (7 9) 12 b 3))
