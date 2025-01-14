;Sa se construiasca o functie care intoarce maximul atomilor numerici
;dintr-o lista, de la orice nivel.

;                      {nil, n = 0                        
;numere(lista: list) = {extrage-numere(l1) (+) extrage-numere(ln), l1: lista
(defun numere (lista)
  (cond 
    ((null lista) nil)
    (t (apply #'append 
              (mapcar #'extrage-numere lista)))))

;                         {x, x: numar
;extrage-numere(x:list) = {numere(x), x: lista
;                         {nil, altfel
(defun extrage-numere (x)
  (cond
    ((numberp x) (list x))
    ((listp x) (numere x))
    (t nil)))

;numar_maxim(lista: list) = max(numere(lista))
(defun numar_maxim (lista)
    (apply #'max (numere lista)))

(numar_maxim '((1 "dfs" (2 3 (100 "few") 5) (6 (7 8)) "wbeborld")))





; 8. Sa se construiasca o functie care intoarce maximul atomilor numerici
;  dintr-o lista, de la orice nivel.

(defun maxim (lista)
         (mapcan (lambda (x)
                   (cond
                     ((numberp x) (list x))  
                     ((listp x) (maxim x))   
                     (t nil)))              
                 lista))

(apply #'max (maxim '(1 2 3 4 5)) ) 
(apply #'max (maxim '((3 2) (5 (4 6)))) ) 
(apply #'max (maxim '(A (B C) 10 20)) ) 
(apply #'max (maxim '((A B) C D)) ) 

