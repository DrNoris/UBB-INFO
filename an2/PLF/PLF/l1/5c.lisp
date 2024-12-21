;; c) Definiti o functie care determina suma a doua numere in reprezentare de
;; lista si calculeaza numarul zecimal corespunzator sumei.

#||
                          {0, n = 1
construire(l1..ln: lista)={10 * construire(l2..ln) + l1, n > 1
||#
(defun construire (lista)
  (cond
    ((null lista) 0)
    (t (+ (* 10 (construire (cdr lista))) (car lista)))))


#||
                                      {aux, n = 0
inv(l1..ln: lista, aux1..auxn: lista)={inv(l2..ln, l1 (+) aux), altfel
||#

(defun inv (l aux)
    (cond
        ((null l) aux)
        (t (inv (cdr l) (cons (car l) aux)))
    )
)

(defun inversa (l) 
  (inv l '()))

#||
                                         {construire(inversa(l1..ln)), k = 0
suma-liste(l1..ln: lista, k1..kn: lista)={construire(inversa(l1..ln)) + construire(inversa(k1..kn)), n != 0 si k != 0
                                         {construire(inversa(k1..kn)), n = 0
||#
(defun suma-liste (lista1 lista2)
  (cond
    ((null lista1) (construire (inversa lista2))) 
    ((null lista2) (construire (inversa lista1))) 
    (t (+ (construire (inversa lista1))          
          (construire (inversa lista2))))))

(suma-liste '(1 2 3) '(4 5 6))



