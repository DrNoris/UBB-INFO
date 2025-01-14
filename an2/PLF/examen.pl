/*
                          {[], n = 0
    construire(N, l1..ln)={[N,l1] (+) construire(N, l2..ln), n > 0

flux(i, i, o)

construire(N, L, R)
N:numar
L:lista de numere
R:lista rezultata ce va avea perechi
*/

construire(_, [], []).
construire(N, [H|T], [[N, H] | R]) :-
    construire(N, T, R).

/*
                         {[], n = 0
construire_main(l1...ln)={construire(l1, l2..ln) (+) construire_main(l2..ln), n > 0

flux(i, o)

construire_main(L, R):
L:multimea numerelor date in cerinta
R:Lista cu perechiile formate
*/

adauga([], _).
adauga([H|T], [H|R]):-
    adauga(T, R).

construire_main([_], []).
construire_main([H|T], [Perechi | R]) :-
    construire(H, T, Perechi),
    construire_main(T, R).