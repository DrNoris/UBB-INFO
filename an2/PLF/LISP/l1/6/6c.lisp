; c) Sa se determine numarul tuturor sublistelor unei liste date, pe orice
; nivel. Prin sublista se intelege fie lista insasi, fie un element de pe
; orice nivel, care este lista. Exemplu: (1 2 (3 (4 5) (6 7)) 8 (9 10)) =>
; 5 (lista insasi, (3 ...), (4 5), (6 7), (9 10)).

(defun sublista(lista)
    (cond
    ((null lista) 1)
    ((listp (car lista))(+ (sublista (car lista)) (sublista (cdr lista))))
    (t(sublista (cdr lista)))
    )
)

(sublista '(1 2 (3 (4 5) () (6 7)) 8 (9 10)))