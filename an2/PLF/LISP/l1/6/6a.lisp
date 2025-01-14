; a) Sa se scrie de doua ori elementul de pe pozitia a n-a a unei liste
; liniare. De exemplu, pentru (10 20 30 40 50) si n=3 se va produce (10 20
; 30 30 40 50).

(defun insert (lista poz)
    (cond
    ((null lista) nil)
    ((= poz 1) (cons (car lista) lista))
    (t(cons (car lista) (insert (cdr lista) (- poz 1)))))
)

(insert '(10 20 30 40 50) 3)