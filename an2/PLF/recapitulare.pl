%R 17
%1
f([],0).
f([H|T],P):- H mod 2 =\= 0, !, f(T,P1), P is P1 + 1.
f([_|T],P):- f(T,P1), P is P1.


%2
sterge([], _, []).
sterge([_|T], I, Rez):- 
    I mod 4 =:= 2,
    !,
    I1 is I + 1,
    sterge(T, I1, Rez).
sterge([H|T], I, [H|Rez]):- 
    I1 is I + 1,
    sterge(T, I1, Rez).

%3
combinatie(_, 0, []).
combinatie([H|T], K, [H|Rez]) :-
    K > 0,
    K1 is K - 1,
    combinatie(T, K1, Rez).
combinatie([_|T], K, Rez) :-
    K > 0,
    combinatie(T, K, Rez).

progresie_aritmetica(Lista):-
    progresie_aritmetica_aux(Lista).

progresie_aritmetica_aux([_]).
progresie_aritmetica_aux([_,_]).
progresie_aritmetica_aux([A, B, C | T]):-
    D1 is B - A,
    D2 is C - B,
    D1 =:= D2,
    progresie_aritmetica_aux([B, C | T]).

main(Lista, K, Rez):-
    findall(SortedLista, (combinatie(Lista, K, Sub), sort(Sub, SortedLista), progresie_aritmetica(SortedLista)), Rez).

%R1 
%2
eliminare([], _, []).
eliminare([H | T], N, Rez):-
    N > 0,
    is_list(H),
    length(H, Len),
    Len mod 2 =:= 0,
    !,
    N1 is N - 1,
    eliminare(T, N1, Rez).
eliminare([H | T], N, [H | Rez]) :-
    N =:= 0,
    !,
    eliminare(T, N, Rez).
eliminare([H | T], N, [H | Rez]) :-
    \+ (is_list(H), length(H, Len), Len mod 2 =\= 0),
    eliminare(T, N, Rez).

%3
submultime([], []).
submultime([H|T],[H|Rez]):-
    submultime(T, Rez).
submultime([_|T], Rez):-
    submultime(T, Rez).

verifica([], 0).
verifica([H|T], S):-
    S >= H,
    S1 is S - H,
    verifica(T, S1).

main3(Lista, Suma, Rez):-
    findall(Sub, (submultime(Lista, Sub), verifica(Sub, Suma)), Rez).

%R16
%1
suma_gaus(N, Suma):- 
   Suma is N * (N + 1) / 2.

suma_recursiva(0, 0).
suma_recursiva(N, Suma1):-
    N > 0,
    N1 is N - 1,
    suma_recursiva(N1, Suma),
    Suma1 is Suma + N.

%2
aranj(_, 0, []).
aranj(Lista, K, [H|T]) :-
    K > 0,
    K1 is K - 1,
    select(H, Lista, Rest),
    aranj(Rest, K1, T).

verificare2([], 0).
verificare2([H|T], S) :-
    S >= H,
    S1 is S - H,
    verificare2(T, S1).

main3(Lista, K, S, Rez):-
    findall(Sub, (aranj(Lista, K, Sub), verificare2(Sub, S)), Rez).


perm([], []).
perm(L, [H|Rez]):-
    selec(H,L,Rest),
    perm(Rest,Rez).

selec(H, [H|T], T).
selec(E, [H|T], [H|S]):-
    selec(E, T, S).

aranj2(_, 0, []).
aranj2(Lista, K, [H|T]):-
    K > 0,
    K1 is K - 1,
    select(H, Lista, Rest),
    aranj2(Rest, K1, T).

select(H, [H|T], T).
select(E, [H|T], [H|S]):- 
    select(E, T, S).

perm([], []).
perm(Lista, [H|T]):- 
    select(H, Lista, Res),
    perm(Res, T).

comb(_, 0, []).
comb([H|T], K, [H|Res]):- 
    K > 0,
    K1 is K - 1,
    comb(T, K1, Res).
comb([_|T], K, Res):- 
    K > 0,
    comb(T, K, Res).

sub([], []).
sub([H|T], [H|R]):- 
    sub(T, R).
sub([_|T], R):- 
    sub(T, R).