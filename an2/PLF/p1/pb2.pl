/*
2.a  Sa se scrie un predicat care determina cel mai mic multiplu comun al
 elementelor unei liste formate din numere intregi.
*/

cmmdc(X, 0, X) :- X \= 0, !.
cmmdc(X, Y, G) :- Y \= 0,
                  R is X mod Y,
                  cmmdc(Y, R, G).

cmmc(X, Y, C) :- 
        cmmdc(X, Y, G),
        C is (X * Y) // G.

cmmc_list([X], X).

cmmc_list([X1, X2 | T], C) :-
        cmmc(X1, X2, C1),
        cmmc_list([C1 | T], C).

/*
b. Sa se scrie un predicat care adauga dupa 1-ul, al 2-lea, al 4-lea, al
 8-lea ...element al unei liste o valoare v data.

 modifica(L, val, poz, i, REZ)
*/

modifica([], _, _, _, []). 

modifica([H | T], V, P, I, [H | R]) :- 
            P > I,
            I1 is I + 1,
            modifica(T, V, P, I1, R).

modifica([H | T], V, P, I, [H, V | R]) :-
            P =:= I,
            P1 is P * 2,
            I1 is I + 1,
            modifica(T, V, P1, I1, R).

mod(L, V, R) :- modifica(L, V, 1, 1, R).
