# R4. Într-o urnă Ul sunt 2 bile numerotate cu 1, 3 bile numerotate cu 2, 2 bile numerotate cu 3 
# gi 1 bilă numerotata cu 4, iar intr-o urnă U2 sunt 2 bile numerotate cu 1, 
# 1 bilă numerotat cu 2, 1 bilă numerotata cu 3 si 1 bilã numerotata cu 4. 
# Se extrage o bilã din U1, care este pus in U2, iar apoi se extrage o bilă din U2. 
# Se notează cu Y numărul extras din U2.
# a) Simulati 1000 de valori pentru Y.
# b) Afisati histograma frecventelor absolute pentru valorile obtinute la a).
# c) Estimati valoarea medie a lui Y.
from random import choice
from matplotlib.pyplot import hist, grid, show
import numpy
from scipy.stats import uniform

def pcta():
    u1 = "1122334"
    u2 = "11234"

    bila = choice(u1)
    u2 = u2 + bila
    bila = choice(u2)

    return int(bila)

valori = []
for _ in range(1000):
    valori.append(pcta())

hist(valori, [k + 0.5 for k in range(5)], density=True, edgecolor='green', alpha=0.7, label='Frecvențe relative')
grid()
show()

valoare_medie = sum(valori) / len(valori)
print(f"Valoarea medie estimată a lui Y este: {valoare_medie:.4f}")


    

    
