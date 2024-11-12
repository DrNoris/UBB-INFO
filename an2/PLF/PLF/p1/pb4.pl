/*
4 a. Sa se scrie un predicat care substituie intr-o lista un element printr-o
 alta lista.
*/

subs([], _, []).
subs([H|T], V, R) :- 
    H =\= V, !,
    subs(T, V, R1),
    R = [H|R1].
subs([V|T], V, R) :- 
    subs(T, V, R1),
    R = [[9,9,9]|R1].

/*
b. Sa se elimine elementul de pe pozitia a n-a a unei liste liniare.
*/

elim([], _, _, []).
elim([H|T], Poz, N, [H|R]) :-
    Poz =\= N,
    Poz1 is Poz + 1,
    elim(T, Poz1, N, R).
elim([_|T], N, N, T) :- !.   

eliminare(L, N, R) :- elim(L, 1, N, R).  