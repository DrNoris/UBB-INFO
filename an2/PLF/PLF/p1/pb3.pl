/*
3.a Sa se scrie un predicat care transforma o lista intr-o multime, in
ordinea primei aparitii. Exemplu: [1,2,3,1,2] e transformat in [1,2,3].
*/

in_list([], _) :- false, !.  
in_list([H | _], H) :- true, !.
in_list([_ | T], X) :- in_list(T, X).


transforma([], _):- !.

transforma([H | T], R) :- 
    in_list(R, H),
    transforma(T, R).
    
transforma([H | T], R) :- 
    not(in_list(R, H)),
    transforma(T, R1),
    R = [H|R1].



/*
b. Sa se scrie o functie care descompune o lista de numere intr-o lista de
 forma [ lista-de-numere-pare lista-de-numere-impare] (deci lista cu doua
 elemente care sunt liste de intregi), si va intoarce si numarul
 elementelor pare si impare.
*/

pare([], [], 0).
pare([H|T], [H|R], F) :- 
        H mod 2 =:= 0, !,
        pare(T, R, F1),
        F is F1 + 1.
pare([_|T], R, F) :-
        pare(T, R, F).

impare([], [], 0).
impare([H|T], [H|R], F) :- 
        H mod 2 =\= 0, !,
        impare(T, R, F1),
        F is F1 + 1.
impare([_|T], R, F) :-
        impare(T, R, F).

construire(L, PARE, IMPARE, NRP, NRI):-
    pare(L, PARE, NRP),
    impare(L, IMPARE, NRI).
    