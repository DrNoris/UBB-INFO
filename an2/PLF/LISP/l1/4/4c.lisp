; c) Sa se scrie o functie care plecand de la o lista data ca argument,
; inverseaza numai secventele continue de atomi. Exemplu:
; (a b c (d (e f) g h i)) ==> (c b a (d (f e) i h g))

(defun inversare (lista)
    (cond
    ((null lista) nil)
    ((listp (car lista)) (cons (inversare(car lista)) (inversare(cdr lista))))
    (t(append (inversare (cdr lista)) (list (car lista))))))

(inversare '(a b c (e f) g h i))