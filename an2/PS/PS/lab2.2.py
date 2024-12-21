from matplotlib.pyplot import axis, plot, show # type: ignore
from random import random
from math import dist

axis('square')
axis((0, 1, 0, 1))

for _ in range(5000):
    F=[random(),random()]

    ascutit = 0
    obtuz = 0

    if 1 - dist (F, [0, 0])**2 - dist(F, [0,1])**2 / (-2 * dist (F, [0, 0]) * dist (F, [1, 0])) > 1:
        ascutit = ascutit + 1
    else:
        obtuz = obtuz + 1

    if 1 - dist (F, [0, 1])**2 - dist(F, [1,1])**2 / (-2 * dist (F, [0, 1]) * dist (F, [1, 1])) > 1:
        ascutit = ascutit + 1
    else:
        obtuz = obtuz + 1

    if 1 - dist (F, [1, 1])**2 - dist(F, [1,0])**2 / (-2 * dist (F, [1, 1]) * dist (F, [1, 0])) > 1:
        ascutit = ascutit + 1
    else:
        obtuz = obtuz + 1

    if 1 - dist (F, [1, 0])**2 - dist(F, [0,0])**2 / (-2 * dist (F, [1, 0]) * dist (F, [0, 0])) > 1:
        ascutit = ascutit + 1
    else:
        obtuz = obtuz + 1

    if obtuz == ascutit:
        plot(F[0],F[1],'bo')
    else:
        plot(F[0],F[1],'ro')

show()