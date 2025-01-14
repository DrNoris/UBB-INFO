; 12. Definiti o functie care inlocuieste un nod cu altul intr-un arbore n-ar
;  reprezentat sub forma (radacina lista_noduri_subarb1...lista_noduri_subarbn)
;  Ex: arborelele este (a (b (c)) (d) (e (f))) si nodul 'b se inlocuieste cu
;  nodul 'g => arborele (a (g (c)) (d) (e (f)))
(defun inlocuire (arb nodv nodn)
    (mapcar (lambda (x)
    (cond 
    ((equal x nodv) nodn)
    ((listp x) (inlocuire x nodv nodn))
    (t x)
    )) arb)
)

(inlocuire '(a (b (c)) (d) (e (f))) 'b 'g)