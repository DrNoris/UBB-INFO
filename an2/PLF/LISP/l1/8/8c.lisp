; c) Sa se construiasca multimea atomilor unei liste.Exemplu: (1 (2 (1 3 (2 4)
; 3) 1) (1 4)) ==> (1 2 3 4)

(defun exista (lista e)
    (cond
    ((null lista) nil)
    ((= (car lista) e) t)
    (t(exista (cdr lista) e))
    )
)

(defun construire(lista new)
    (cond
    ((null lista) new)
    ((listp (car lista)) (construire (car lista) new))
    ((not (exista new (car lista))) (construire (cdr lista) (append new (list(car lista)))))
    (t (construire(cdr lista) new))
    )
)



(construire '(1 (2 (1 3 (2 4) 3) 1) (1 4)) '())