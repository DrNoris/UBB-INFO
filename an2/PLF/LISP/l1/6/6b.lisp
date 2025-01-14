; b) Sa se scrie o functie care realizeaza o lista de asociere cu cele doua
; liste pe care le primeste. De ex: (A B C) (X Y Z) --> ((A.X) (B.Y)
; (C.Z)).

(defun perechi(lista1 lista2)
    (cond 
    ((or (null lista1) (null lista2)) nil)
    (t(append (list(cons (car lista1) (car lista2))) (perechi (cdr lista1) (cdr lista2))))
    )
)

(perechi '(A B C) '(X Y Z))