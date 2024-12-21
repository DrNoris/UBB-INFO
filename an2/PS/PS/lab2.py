from random import randint
from math import perm 
from matplotlib.pyplot import plot, grid, title, show # type: ignore


probabilitate = 0

def generator(n = 26):
    zile = []
    global probabilitate

    for i in range(n):
        zile.append(randint(1, 365))

    zile.sort()

    for i in range(n - 1):
        if zile[i] == zile[i+1]:
            probabilitate = probabilitate + 1
            break



def prob(n = 1000):
    global probabilitate
    cn = n 
    while n != 0:
        n = n - 1
        generator()

    probabilitate = probabilitate / cn
    print(probabilitate)

# prob()
# generator()

def punctb(n = 26):
    print(1 - perm(365, n) / 365 ** n)


#punctb()


def grafic(x = 50, nr_sim = 1000):
    title('Plot test')
    xs = range(10)
    ys = [x*x/100 for x in xs]
    plot(xs,ys,'r*')
    grid()
    show()
    
#grafic()

