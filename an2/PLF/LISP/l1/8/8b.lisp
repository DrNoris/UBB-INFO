; b) Definiti o functie care determina succesorul unui numar reprezentat cifra
; cu cifra intr-o lista. De ex: (1 9 3 5 9 9) --> (1 9 3 6 0 0)

(defun solutie(lista)
    (+ 1 (numar-aux lista 0))
)

(defun numar-aux (lista acumulator)
  (cond
    ((null lista) acumulator)
    (t (numar-aux (cdr lista) (+ (* acumulator 10) (car lista))))))

(solutie '(1 9 3 5 9 9))