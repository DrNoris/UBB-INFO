/*
5.
a) Sa se determine pozitiile elementului maxim dintr-o lista liniara. De ex:
 poz([10,14,12,13,14], L) va produce L = [2,5].
 */

% Cazul de bază pentru lista goală
poz([], _, _, []).

% Cazul când găsim un element mai mare decât Max, actualizăm Max și continuăm căutarea
poz([H|T], I, Max, [I|Poz]) :-
    H > Max,
    poz(T, I1, H, Poz),
    I1 is I + 1.

% Cazul când găsim un element egal cu Max, adăugăm poziția la rezultat
poz([H|T], I, Max, [I|Poz]) :-
    H == Max,
    poz(T, I1, Max, Poz),
    I1 is I + 1.

poz([H|T], I, Max, Poz) :-
    H < Max,
    poz(T, I1, Max, Poz),
    I1 is I + 1.






