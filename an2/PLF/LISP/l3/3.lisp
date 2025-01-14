; 3. Sa se construiasca o functie care verifica daca un atom e membru al
;  unei liste nu neaparat liniara.
(defun SAU(list)
    (cond
    ((null list) nil)
    ((car list) t)
    (t(SAU (cdr list)))
    )
) 

(defun cautare (list e)
  (cond
   ((null list) nil) 
   ((atom list) (equal list e))
   ((equal (car list) e) t)
   (t (SAU (mapcar (lambda (x) (cautare x e)) list)))))


(cautare '(1 2 (3 4 (A)) Z) 3)
(cautare '(1 2 (3 4 (A)) Z) 'Z)
(cautare '(1 2 (3 4 (A)) Z) 'A)
(cautare '(1 2 (3 4 (A)) Z) 17)