; Definiti o functie care obtine dintr-o lista data lista tuturor atomilor
; care apar, pe orice nivel, dar in aceeasi ordine. De exemplu:
; (((A B) C) (D E)) --> (A B C D E)

(defun liniar(lista)
    (cond 
    ((null lista) nil)
    ((listp (car lista)) (append (liniar (car lista)) (liniar (cdr lista))))
    (t(append (list(car lista)) (liniar (cdr lista)))))
)

(liniar '(((A B) C)(D E)))