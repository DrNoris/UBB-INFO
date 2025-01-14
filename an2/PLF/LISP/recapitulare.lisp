(DEFUN F(L) 
(MAX (CAR L) (CADDR L)))
(SETQ G #'F)

(SETQ F 10)
(G (8 11 2 3 7 9))