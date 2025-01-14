; b) Sa se construiasca o functie care verifica daca un atom e membru al unei
; liste nu neaparat liniara.
(defun cautare(e lista)
    (cond
    ((null lista) nil)
    ((equal e (car lista)) t)
    ((listp (car lista)) (cautare e (car lista)))
    (t(cautare e (cdr lista)))))


(cautare 'H '(((A B) 2) (D A E) A))
(cautare '2 '(((A B) 2) (D A E) A))