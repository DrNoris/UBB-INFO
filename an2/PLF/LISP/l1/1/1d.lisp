; d) Sa se scrie o functie care determina numarul de aparitii ale unui atom dat
; intr-o lista neliniara.

(defun frecventa (e lista)
  (cond
    ((null lista) 0) 
    ((equal e (car lista)) 1)  
    ((listp (car lista))  
     (+ (frecventa e (car lista)) (frecventa e (cdr lista))))  ; Adaugă frecvența din sublistă și din restul listei
    (t (frecventa e (cdr lista)))))  ; Continuă recursiv pentru restul listei

(defun numara-aparitii (e lista)
  (frecventa e lista))

(numara-aparitii 'A '((((C A) A B) 2) (D A E) A))  

