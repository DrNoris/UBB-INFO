/*
1.a. Sa se scrie un predicat care intoarce diferenta a doua multimi.
                    {true, X=l1
apartine(X, l1..ln)={false, n=0
                    {apartine(X, l2..ln), altfel

Flux: (i,i)

apartine(X, L1)
    X: variabila
    L1: lista
*/      

apartine(_, []):-false, !.
apartine(X, [X|_]):- true, !.
apartine(X, [_|T]):- apartine(X, T).

/*

                            {[], n=0,
dif_multime(l1..ln, k1..km)={dif_multime(l2..ln, k1..km), apartine(l1, k1..km)=true
                            {l1 + dif_multime(l2..ln, k1..km), altfel

Flux: (i,i,i) si (i,i,o)

dif_multime(L1, L2, R)
    L1 : prima multime
    L2 : a 2-a multime
    R : lista raspuns
*/

dif_multime([], _, []):- !.
dif_multime([H|T], L, R):- apartine(H, L),
                        dif_multime(T, L, R).
dif_multime([H|T], L, R):- not(apartine(H, L)),
                            dif_multime(T, L, R1),
                            R = [H|R1].




/*
1.bSa se scrie un predicat care adauga intr-o lista dupa fiecare element par
 valoarea 1.

               {[], n=0
insert(l1..ln)={l1 + insert(l2..ln), l1 % 2 = 1
               {l1 + 1 + insert(l2..ln), altfel

insert(L)
    L: lista cu numere

Flux: (i, o)
*/

insert([], []).
insert([H|T], [H, 1|R]):- H mod 2 =:= 0,
                        insert(T, R).
insert([H|T], [H|R]):- H mod 2 =:= 1,
                        insert(T, R).