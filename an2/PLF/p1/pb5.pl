/*
5.
 a. Sa se scrie un predicat care sterge toate aparitiile unui anumit atom
 dintr-o lista.
*/

sterge([], _, []).
sterge([H|T], H, R) :-
    sterge(T, H, R).
sterge([H|T], Atom, [H|R]) :-
    H \= Atom,
    sterge(T, Atom, R).

/*
b. Definiti un predicat care, dintr-o lista de atomi, produce o lista de
 perechi (atom n), unde atom apare in lista initiala de n ori. De ex:
 numar([1, 2, 1, 2, 1, 3, 1], X) va produce X = [[1, 4], [2, 2], [3, 1]].
*/

in_list([], _) :- false, !.  
in_list([H | _], H) :- true, !.
in_list([_ | T], X) :- in_list(T, X).

count([], _, 0).                    
count([H|T], H, C) :-            
    count(T, H, C1),    
    C is C1 + 1.          
count([H|T], E, C) :-      
    H \== E,                   
    count(T, E, C).  

numar([], [], _).  
numar([H|T], [[H, F]|X], R) :-
    not(in_list(R, H)), !,        
    count([H|T], H, F),      
    numar(T, X, [H|R]).       
numar([_|T], X, R) :-  
    numar(T, X, R).  