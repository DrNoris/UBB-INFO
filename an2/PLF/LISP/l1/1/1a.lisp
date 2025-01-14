; a) Sa se insereze intr-o lista liniara un atom a dat dupa al 2-lea, al 4-lea,
; al 6-lea,....element.

(defun insert (e pozitie lista)
    (cond 
    ((null lista) nil)
    ((= 0 (mod pozitie 2))
        (cons (car lista) (cons e (insert e (+ 1 pozitie) (cdr lista)))))
    (t (cons (car lista) (insert e (+ 1 pozitie) (cdr lista))))))

(insert 100 1 '(1 2 3 4 5 6 7 8 9))