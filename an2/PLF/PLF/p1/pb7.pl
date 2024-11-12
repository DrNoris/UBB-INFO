/*
7.a. Sa se scrie un predicat care intoarce reuniunea a doua multimi.
*/

in_list([], _) :- false, !.  
in_list([H | _], H) :- true, !.
in_list([_ | T], X) :- in_list(T, X).

construire([], _).
construire([H|T], [H|R]) :-
    \+ in_list(R, H),
    construire(T, R).
construire([H|T], R) :-
    in_list(R, H), 
    construire(T, R).
    
adauga([], L, L).
adauga([H|T], L, [H|R]) :-
    adauga(T, L, R).


reuniunea(L1, L2, R) :-
    adauga(L1, L2, L),
    construire(L, R).


/*
7.b. Sa se scrie un predicat care, primind o lista, intoarce multimea
 tuturor perechilor din lista. De ex, cu [a, b, c, d] va produce
 [[a, b], [a, c], [a, d], [b, c], [b, d], [c, d]].
*/
pereche([], _, []).
pereche([H|T], V, R):-
    pereche(T, V, R1),
    R = [[V, H]|R1].

perechi([], []).
perechi([_], []).
perechi([H|T], R) :-
    pereche(T, H, R1),
    perechi(T, R2), !,
    append(R1, R2, R).