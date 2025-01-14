; 5. Definiti o functie care testeaza apartenenta unui nod intr-un arbore n-ar
;  reprezentat sub forma (radacina lista_noduri_subarb1... lista_noduri_
;  _subarbn)
;  Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul este 'b => adevarat
(defun SAU(list)
    (cond
    ((null list) nil)
    ((car list) t)
    ((listp (car list)) (append (SAU (car list)) (SAU (cdr list))))
    (t(SAU (cdr list)))
    )
) 

(defun apartine (arb nod)
    (cond 
    ((null arb) nil)
    ((equal (car arb) nod) t)
    (t (SAU (mapcar (lambda (subarb) (apartine subarb nod)) (cdr arb))))))  ; Combina rezultatele.

(apartine '(a (b (c)) (d) (e (f))) 'b)  ; => T
(apartine '(a (b (c)) (d) (e (f))) 'd)  ; => T
(apartine '(a (b (c)) (d) (e (f))) 'f)  ; => T
(apartine '(a (b (c)) (d) (e (f))) 'g)  ; => NIL


; Să se definească o funcție MODIF care să modifice o listă dată ca parametru astfel: atomii
; nenumerici rămân nemodificaţi iar cei numerici îşi dublează valoarea; modificarea trebuie
; făcută la toate nivelurile.
(defun modif (list)
    (cond
    ((numberp list) (* 2 list))
    ((atom list) list)
    (t(mapcar #'modif list))
    ))

(modif '(1 (b (4) c) (d (3 (5 f))))) 

; Să se construiască o funcţie LGM ce determină lungimea (calculată în număr de elemente la
; nivel superficial) celei mai lungi subliste dintr-o listă dată L (dacă lista este formată numai
; din atomi atunci lungimea cerută este chiar cea a listei L). 
(defun lgm (lista)
    (cond 
    ((atom lista) 0)
    (t(MAX (length lista) (apply #'max (mapcar #'lgm lista))))
    )
)

(lgm '(1 (2 (3 4) (5 (6)) (7)))) 