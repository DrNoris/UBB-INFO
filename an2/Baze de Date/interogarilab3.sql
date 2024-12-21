--1. Cati bucatari cu salariu mai mare de 35 avem pe fiecare specializare din restaurant?
SELECT specialitate_bucatar, COUNT(specialitate_bucatar) as [Numar Total]
FROM Bucatari
WHERE Bucatari.salariu_bucatar > 35
GROUP BY (specialitate_bucatar);

--2. Pretul mediu al preparatelor servite de fiecare ospatar ce a servit mai mult de 10 preparate?
SELECT Ospatari.nume_ospatar, AVG(Preparate.pret_preparat) as Pret
FROM Serviri
JOIN Preparate on Serviri.id_preparat = Preparate.id_preparat
JOIN Ospatari on Serviri.id_ospatar = Ospatari.id_ospatar
GROUP BY Ospatari.nume_ospatar
HAVING COUNT(Serviri.id_ospatar)>10;

--3. Preparate ce au adus un venit de peste 100 de lei in restaurant?
SELECT p.nume_preparat, COUNT(p.id_preparat) * p.pret_preparat as Venit_Total
FROM Preparate p
JOIN Serviri s on p.id_preparat = s.id_preparat
GROUP BY p.nume_preparat, p.pret_preparat
HAVING COUNT(p.id_preparat) * p.pret_preparat > 100;

--4. Preparate ce contin Ulei de masline m-n
SELECT p.nume_preparat, p.ingrediente_preparat
FROM Preparate p
JOIN Preparat_Ingrediente pi on pi.id_preparat = p.id_preparat
JOIN Ingrediente i on pi.id_ingredient = i.id_ingredient
WHERE i.nume_ingredient = 'Ulei de masline';

--5. Numele curierilor ce au livrat la clientul ce a comandat cel mai mult.
SELECT DISTINCT c.nume_curier
FROM Curieri c
JOIN ComenziOnline co ON c.id_curier = co.id_curier
WHERE co.nume_client = (
    SELECT top 1 co.nume_client
    FROM ComenziOnline co
    JOIN Comenzi_Preparate cp on cp.id_comandaOnline = co.id_comandaOnline
    JOIN Preparate p on p.id_preparat = cp.id_preparat
    GROUP BY co.nume_client
    order by sum(p.pret_preparat) desc
)

--6. Numele curierilor ce au transportat preparate ce contin fructe.
SELECT DISTINCT nume_curier
FROM Curieri c 
JOIN ComenziOnline co ON c.id_curier = co.id_curier
JOIN Comenzi_Preparate ON Comenzi_Preparate.id_comandaOnline = co.id_comandaOnline
JOIN Preparate p On Comenzi_Preparate.id_preparat = p.id_preparat
JOIN Preparat_Ingrediente pi ON p.id_preparat = pi.id_preparat
JOIN Ingrediente i on i.id_ingredient = pi.id_ingredient
WHERE i.nume_ingredient in ('Lamai', 'Mere', 'Banane', 'Portocale')

--7. Preparatele cu pretulul mai mare de 10 servite de ospatari cu salariu mai mare de 10 la ora
SELECT distinct Preparate.nume_preparat
FROM Preparate
JOIN Serviri s on s.id_preparat = Preparate.id_preparat
JOIN Ospatari o on o.id_ospatar = s.id_ospatar
WHERE pret_preparat > 10 and o.salariu_ospatar > 10;

--8. Lista unica a clientilor ce au facut rezervari la o masa cu capacitate mai mare de 6 m-n
SELECT DISTINCT r.nume_client
FROM Rezervari r
JOIN Rezervari_Mese rm ON r.id_rezervare = rm.id_rezervare
JOIN Mese m ON rm.id_masa = m.id_masa
WHERE m.capacitate_masa > 6;

--9. Ospatarul ce a servit ultima data Paine
SELECT top 1 Ospatari.nume_ospatar
FROM Serviri
RIGHT JOIN Ospatari on Serviri.id_ospatar = Ospatari.id_ospatar
JOIN Preparate p on p.id_preparat = Serviri.id_preparat 
WHERE p.nume_preparat = 'Paine'
ORDER BY data_servire DESC

--10. Ospatarii afisati in ordinea descrescatoare a locurilor totale ale meselor pe care le gestioneaza
SELECT o.nume_ospatar, SUM(m.capacitate_masa) AS Capacitate_Totala
FROM Ospatari o
JOIN Mese m ON o.id_ospatar = m.id_ospatar
GROUP BY o.nume_ospatar
ORDER BY SUM(m.capacitate_masa) DESC;


