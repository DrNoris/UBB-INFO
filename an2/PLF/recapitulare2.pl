%R1
%2
selec(H, [H|T], T).
selec(E,[H|T], [H|R]):- 
    selec(E, T, R).

aranj(_, 0, []).
aranj(Lista, K, [H|T]):-
    K > 0,
    K1 is K - 1,
    selec(H, Lista, Rest),
    aranj(Rest, K1, T).

verificare([], 1).
verificare([H|T], P):- 
    verificare(T, P1),
    P is P1 * H.

main(Lista, K, P, Rez):- 
    findall(Sub, (aranj(Lista, K, Sub), verificare(Sub, P)), Rez).

