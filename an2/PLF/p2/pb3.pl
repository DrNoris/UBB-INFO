/*
3.
a) Sa se sorteze o lista cu eliminarea dublurilor. De ex: [4 2 6 2 3 4] => [2 3 4 6]
*/

inst_sort(X, [], [X]).
inst_sort(X, [Y | T], [X, Y | T]) :- X < Y, !.
inst_sort(X, [Y | T], [X | T]) :- X == Y, !.
inst_sort(X, [Y | T], [Y | Sorted]) :- 
    X > Y,
    inst_sort(X, T, Sorted).

insertion_sort([], []).
insertion_sort([H|T], Sorted) :-
    insertion_sort(T, SortedTail),
    inst_sort(H, SortedTail, Sorted).
