/*
6.
 a. Sa se scrie un predicat care elimina dintr-o lista toate elementele care
 se repeta (ex: l=[1,2,1,4,1,3,4] => l=[2,3])
*/

count([], _, 0).
count([H|T], H, C) :-
    count(T, H, C1),
    C is C1 + 1.
count([H|T], V, C) :-
    V \== H,
    count(T, V, C).

eliminare_duplicate([], _, []).
eliminare_duplicate([H|T], H, R) :- 
    eliminare_duplicate(T, H, R).
eliminare_duplicate([H|T], V, [H|R]) :- 
    H \== V,
    eliminare_duplicate(T, V, R).


elimina([], []).
elimina([H|T], R) :-
    count(T, H, Count),
    Count \== 0,
    eliminare_duplicate(T, H, E),
    elimina(E, R).
elimina([H|T], [H|R]):-
    count(T, H, Count),
    Count == 0,
    elimina(T, R).

/*
b. Sa se elimine toate aparitiile elementului maxim dintr-o lista de numere
 intregi.
*/

% Base case for finding the max value: if the list is empty, the max is 0 (or another base value)
max_value([], 0).
max_value([H|T], H) :-
    max_value(T, Max),
    H >= Max.
max_value([H|T], Max) :-
    max_value(T, Max),
    H < Max.

% Count occurrences of a value in a list
count_occurrences([], _, 0).
count_occurrences([H|T], H, Count) :-
    count_occurrences(T, H, C1),
    Count is C1 + 1.
count_occurrences([H|T], V, Count) :-
    H \== V,
    count_occurrences(T, V, Count).

% Main predicate to find the max value and its count
max_count(List, Max, Count) :-
    max_value(List, Max),
    count_occurrences(List, Max, Count).

