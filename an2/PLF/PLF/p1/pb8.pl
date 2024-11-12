/*
8. a. Sa se scrie un predicat care testeaza daca o lista este multime.
*/

in_list([], _) :- false, !.
in_list([H|_], H) :- true, !.
in_list([_|T], V) :- 
        in_list(T, V).

multime([]). 
multime([H|T]) :-
    in_list(T, H), !,
    false.
multime([H|T]) :-
    \+ in_list(T, H),
    multime(T).

    
/*
b. Sa se scrie un predicat care elimina primele 3 aparitii ale unui element
 intr-o lista. Daca elementul apare mai putin de 3 ori, se va elimina de
 cate ori apare.
*/

elimina([], _, _, []).
elimina([V|T], V, C, R) :-
    C > 0, 
    C1 is C - 1, !,
    elimina(T, V, C1, R).
elimina([V|T], V, 0, [V|R]) :-
    elimina(T, V, 0, R).
elimina([H|T], V, C, [H|R]) :-
    H \= V, !,
    elimina(T, V, C, R).
