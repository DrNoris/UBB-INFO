; c) Sa se inlocuiasca fiecare sublista a unei liste cu ultimul ei element.
; Prin sublista se intelege element de pe primul nivel, care este lista.
; Exemplu: (a (b c) (d (e (f)))) ==> (a c (e (f))) ==> (a c (f)) ==> (a c
; f)
; (a (b c) (d ((e) f))) ==> (a c ((e) f)) ==> (a c f)

(defun subs (lista)
  (cond
    ((null lista) nil) 
    ((listp (car lista))
     (cons (subs-ultim (car lista))
           (subs (cdr lista))))
    (t (cons (car lista) (subs (cdr lista))))))

(defun subs-ultim (lista)
  (cond
    ((null (cdr lista))
     (if (listp (car lista))
         (subs-ultim (car lista))
         (car lista)))
    (t (subs-ultim (cdr lista)))))

(subs '(a (b c) (d ((e) f))))
