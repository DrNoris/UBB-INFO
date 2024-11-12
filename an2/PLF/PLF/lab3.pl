/*
2. Fiind dat un numar n pozitiv, se cere sa se determine toate descompunerile
sale ca suma de numere prime distincte.

prim(n)={ true, n=2;
        {  div(n,2), n > 2;

flux(i)

prim(N)
 N: numar.
*/

prim(2).
prim(N) :-
    N > 2,
    \+ div(N, 2).

/*
         {false, F*F > N.
div(N,F)={true, F*F <= N and N % F = 0.
         {div(N, F+1), F*F <= N and N % F != 0.

flux(i,i).

div(N, F)
 N:numar,
 F:numar
*/
div(N, F) :-
    F * F =< N,
    N mod F =:= 0. 
div(N, F) :-
    F * F =< N,
    N mod F =\= 0, 
    F2 is F + 1, 
    div(N, F2).

/*  
                { [], N < 2.
numere_prime(N)={ N (+) numere_prime(N-1), prim(N) and N > 2
                { numere_prime(N-1), prim(N) == false

flux(i,o)

numere_prime(N, L)
N: numar
L: lista
*/
numere_prime(1, []).
numere_prime(2, [2]).
numere_prime(N, [N|R]) :-
    N > 2,
    prim(N),
    N1 is N - 1,
    numere_prime(N1, R).
numere_prime(N, R) :-
    N > 2,
    \+ prim(N),
    N1 is N - 1,
    numere_prime(N1, R).

/*
                {[], N < 2
descompune_suma={l1 (+) descompune_suma(N - l1, l2..ln), N >= l1
                {false, N < l1.

flux(i,i,o,o) (o,i,o,o)

descompune_suma(N, Ps, Acc, Decomp)
N:numar
Ps: lista cu numere prime mai mici sau egale cu N
Acc: lista in care se salveaza numerel
Decomp: lista in care se salveaza raspunsul
*/

descompune_suma(0, _, Acc, Acc).  
descompune_suma(N, [P|Ps], Acc, Decomp) :-
    N >= P,  
    N1 is N - P,
    descompune_suma(N1, Ps, [P|Acc], Decomp).  
descompune_suma(N, [_|Ps], Acc, Decomp) :-
    N > 0, 
    descompune_suma(N, Ps, Acc, Decomp).

/*
descompune(N)={descompune_suma(N,numere_prime(N))

flux(i, o)

descompune(N, Decomp)
N:numar
Decomp:lista
*/

descompune(N, Decomp) :-
    numere_prime(N, Primes),
    descompune_suma(N, Primes, [], Decomp).