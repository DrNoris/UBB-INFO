; 6. Sa se construiasca lista nodurilor unui arbore de tipul (1) parcurs in
;  inordine.

; stanga(l1..ln: lista)=parcurg_st(l3..ln, 0, 0)
(defun stang (arbore)
    (parcurg_st (cddr arbore) 0 0))

; dreapta(l1..ln: lista)=parcurg_dr(l3..ln, 0, 0)
(defun drept (arbore)
    (parcurg_dr (cddr arbore) 0 0))

;                            { nil, n = 0
; parcurg_st(l1..ln, nv, nm)={ nil, nv = nm + 1
;                            { l1 (+) l2 (+) parcurg_st(l3..ln, nv+1, nm+l2), altfel

(defun parcurg_st(arbore nv nm)
    (cond
    ((null arbore) nil)
    ((= nv (+ 1 nm)) nil)
    (t (cons (car arbore) (cons (cadr arbore) (parcurg_st (cddr arbore) (+ 1 nv) (+ (cadr arbore) nm)))))))


;                            { nil, n = 0
; parcurg_dr(l1..ln, nv, nm)={ l1..ln, nv = nm + 1
;                            { parcurg_st(l3..ln, nv+1, nm+l2), altfel


(defun parcurg_dr(arbore nv nm)
    (cond
    ((null arbore) nil)
    ((= nv (+ 1 nm)) arbore)
    (t (parcurg_dr (cddr arbore) (+ 1 nv) (+ (cadr arbore) nm)))))


;                        { [], n = 0
; inordine(l1..ln: list)={ inordine(stang(l1..ln)) (+) l1 (+) inordine(drept(l1..ln))

(defun inordine (arbore)
  (cond 
    ((null arbore) nil)
    (t (append
       (inordine (stang arbore))      
       (list (car arbore))            
       (inordine (drept arbore))))))   


(inordine'(a 2 b 2 c 1 i 0 f 1 g 0 d 2 e 0 h 0))
(inordine'(a 2 b 2 d 1 g 0 e 2 h 1 l 2 m 0 n 0 i 0 c 1 f 2 j 0 k 1 q 1 p 2 r 0 s 0 ))