; a) Sa se scrie o functie care testeaza daca o lista este liniara.

(defun test (lista)
  (cond
    ((null lista) "Lista este liniara.")
    ((listp (car lista)) "Lista nu este liniara.") 
    (t (test (cdr lista))))) 


(test '(a b c d))
(test '(a b c (a) d))