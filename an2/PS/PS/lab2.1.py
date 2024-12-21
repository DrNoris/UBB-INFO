from matplotlib.pyplot import axis, plot, show # type: ignore
from random import random
from math import dist


axis('square')
axis((0, 1, 0, 1))

albastre = 0

for _ in range(1000):
    F=[random(),random()]
    ascutit = 0
    obtuz = 0
    if dist(F, [0.5, 0.5]) < 0.5:
        plot(F[0],F[1],'bo')
        albastre = albastre + 1
    else:
        plot(F[0],F[1],'ro')

albastre = albastre / 1000 * 4

print(albastre)

show()