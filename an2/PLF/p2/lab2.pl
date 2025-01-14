/*
10.a Se da o lista de numere intregi. Se cere sa se adauge in lista dupa 1-ul
element, al 3-lea element, al 7-lea elemen, al 15-lea element … o valoare
data e.
                          {[], n=0
inserare(l1..ln, e, i, p)={inserare(l2..ln, e, i+1, p), i != p, n>0
                          {l1 (+) e (+) inserare(l2..ln, e, i+1, p*2+1), i = p, n > 0

Flux: i,i,i,o

inserare(L, e, i, p, R)
    L: lista
    e: element
    i: numar intreg, pozitia curenta
    p: numar intreg, pozitia de inserare
    R: lista rezultat
*/

inserare([], _, _, _, []).
inserare([H|T], E, I, P, [H|R]):-
        I =\= P,
        I1 is I+1,
        inserare(T, E, I1, P, R).
inserare([H|T], E, I, P, [H, E|R]):-
        I =:= P,
        I1 is I+1,
        P1 is P*2+1,
        inserare(T, E, I1, P1, R).

/*
10.b.Se da o lista eterogena, formata din numere intregi si liste de numere
intregi. Lista incepe cu un numar si nu sunt 2 elemente consecutive care
sunt liste. Se cere ca in fiecare sublista sa se adauge dupa 1-ul, al 3-
lea, al 7-lea… element valoarea care se gaseste inainte de sublista in
lista eterogena

                     {[], n=0 
inserareb(l1..ln, E)={l1 (+) inserareb(l2..ln, l1), l1 - numar, n>0
                     {inserare(l1, E, 1, 1) (+) inserareb(l2..ln, E), l1 - lista, n > 0 

Flux:

inserareb(L, E, R)
        L: lista rezultat
        E: numar intreg unde salvam elementul de inserat
        R: lista rezultat
*/  

inserareb([], _, []).
inserareb([H|T], _, [H|R]):-
        number(H),
        inserareb(T, H, R).
inserareb([H|T], E, [R1|R]):-
        is_list(H),
        inserare(H, E, 1, 1, R1),
        inserareb(T, E, R).