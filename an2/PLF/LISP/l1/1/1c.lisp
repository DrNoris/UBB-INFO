; c) Definiti o functie care intoarce cel mai mare divizor comun al numerelor
; dintr-o lista neliniara.

(defun lista_liniara (lista)
    (cond 
    ((null lista) nil)
    ((listp (car lista))
        (append (lista_liniara (car lista)) (lista_liniara (cdr lista))))
    (t (cons (car lista) (lista_liniara (cdr lista)))))
)

(defun cmmdc (x y)
    (cond 
    ((< x y) (cmmdc x (- y x)))
    ((> x y) (cmmdc (- x y) y))
    ((equal x y) x)))

(defun cmmdc_lista(lista)
    (cond
    ((null (cdr lista)) (car lista))
    (t (cmmdc (car lista) (cmmdc_lista (cdr lista)))))
)

(defun main (lista)
    (setq lista_liniara_ (lista_liniara lista))
    (cmmdc_lista lista_liniara_)
)

(main '(65 (35 (5) 10) 40))

