; b) Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
; care apar, pe orice nivel, dar in ordine inversa. De exemplu: (((A B) C)
; (D E)) --> (E D C B A)

(defun invers (lista)
    (cond
    ((null lista) nil)
    ((listp (car lista))
        (append (invers (cdr lista)) (invers (car lista))))
    (t (append (invers (cdr lista)) (list(car lista))))
))

(invers '(((A B) C)(D E)))