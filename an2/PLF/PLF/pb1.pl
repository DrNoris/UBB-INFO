/*
1.
a) Definiti un predicat care determina suma a doua numere scrise in
 reprezentare de lista.
*/

numar([], 0, 1).
numar([H | T], R, P) :-
    numar(T, R1, P1),
    P is P1 * 10,
    R is R1 + P1*H.

suma([], 0).
suma([H1], Suma) :-
    numar(H1, Suma, _), !.
suma([H1, H2|_], Suma) :-
    numar(H1, S1, _),
    numar(H2, S2, _),
    Suma is S1 + S2.

