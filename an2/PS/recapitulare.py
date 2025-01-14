import random 
import numpy
from random import choices, sample
from math import perm, dist, comb, log
from scipy.stats import binom, uniform, expon, norm, bernoulli
from matplotlib.pyplot import axis, plot, grid, title, show, hist, legend, bar, xticks, yticks
def l2p1a(persoane = 26):
    n = 1000
    probabilitate = 0
    def f(persoane):
        zile = [random.randint(1, 366) for _ in range(persoane)]
        zile.sort()

        for i in range(n - 1):
            if zile[i] == zile[i+1]:
                probabilitate = probabilitate + 1
                break
        return probabilitate
    
    while n:
        n = n - 1
        probabilitate = probabilitate + f(persoane)

    probabilitate = probabilitate / 1000

def l2p1b(n = 26):
    print(1 - perm(365, n)/ 365 ** n)    

def l2p1c():
    title('Plot test')
    xs = range(10)
    ys = [x*x/100 for x in xs]
    plot(xs,ys,'r*')
    grid()
    show()

def l2p2a1():
    axis('square')
    axis((0, 1, 0, 1))

    albastre = 0

    for _ in range(1000):
        F=[random.random(),random.random()]
   
        distanta = (F[0] - 0.5) ** 2 + (F[1] - 0.5) ** 2 

        if distanta < 0.25:
            plot(F[0], F[1], "bo")
            albastre += 1
        else:
            plot(F[0], F[1], "ro")

    show()
        
def l2p2a2():
    axis('square')
    axis((0, 1, 0, 1))

    for _ in range(1000):
        F=[random.random(),random.random()]
   
        distanta_centru = (F[0] - 0.5) ** 2 + (F[1] - 0.5) ** 2 
        distanta_colt = min(((F[0] - 0) ** 2 + (F[1] - 1) ** 2),
                            ((F[0] - 1) ** 2 + (F[1] - 0) ** 2),
                            ((F[0] - 0) ** 2 + (F[1] - 0) ** 2),
                            ((F[0] - 1) ** 2 + (F[1] - 1) ** 2))
        if distanta_centru < distanta_colt:
            plot(F[0], F[1], "bo")
        else:
            plot(F[0], F[1], "ro")

    show()

def l3p1a():
    urna = 'rrrrraaavv'
    n = 1000

    A = 0
    B = 0

    while n:
        n -= 1
        bile = sample(urna, 3)

        if 'r' in bile:
            A += 1
            if bile[0] == bile [1] == bile [2]:
                B += 1

    print (B/A)

def l3p1b():
    PA = 1 - comb(5, 3) / comb(10, 3) 
    PB = comb(5, 3) / comb(10, 3)

    print(PB / PA)

def l3p2(n = 500):
    data = [random.randrange(1, 7) for _ in range(n)]
    bin_edges = [k + 0.5 for k in range(7)]

    hist(data, bin_edges, density=True, rwidth = 0.9, color = 'green', edgecolor = 'black',
    alpha = 0.5, label = 'frecvente relative')

    distribution = dict([(i,1/6) for i in range(1,7)])
    bar(distribution.keys(), distribution.values(), width = 0.85, color = 'red', edgecolor = 'black',
    alpha= 0.6, label = 'probabilitati')
    legend(loc = 'lower left')
    grid()
    show()

def l3p3a(n = 10000):
    urna = "1111110000"
    valori = []
    while n:
        n -= 1
        bile = choices(urna, k=5)
        suma = sum(int(bila) for bila in bile)
        valori.append(suma)

    return valori
    
def l3p3b():
    valori = l3p3a()
    hist(valori, bins=numpy.arange(-0.5, 6.5, 1), density= True, edgecolor='green', alpha=0.7, label='Frecvențe relative')
    p = 6/10
    x = numpy.arange(0, 6)
    n = 5
    teoretic = binom.pmf(x, n, p)
    bar(x, teoretic, width=0.85, alpha=0.6, color='red', edgecolor='black', label='Probabilități teoretice')

    grid(True)
    show()

def l3p3c():
    valori = l3p3a()
    caz_fav = sum([valoare > 2 and valoare < 6 for valoare in valori])
    P = caz_fav / len(valori)
    print(P)

    p_teoretic = binom.pmf(3, 5, 6/10) + binom.pmf(4, 5, 6/10) + binom.pmf(5, 5, 6/10)

def l3p4():
    zar1 = [random.randint(1, 7) for _ in range(1000)]
    zar2 = [random.randint(1, 7) for _ in range(1000)]
    zar3 = [random.randint(1, 7) for _ in range(1000)]

    data = [z1 + z2 + z3 for z1, z2, z3 in zip(zar1, zar2, zar3)]
    hist(data, bins=numpy.arange(2.5, 19.5, 1), density=True, edgecolor='green', alpha=0.7, label='Frecvențe relative')

    grid()
    show()

def l4p1a(p = 0.5, n = 1000):
    start = 0
    for _ in range(n):
        directie = random.random()
        if directie < p:
            directie = -1
        else:
            directie = 1

        start += directie

    return start

def l4p1b(n = 1000):
    pozitii = []

    for _ in range(n):
        pozitii.append(l4p1a(0.5, 10))

    hist(pozitii, bins=20, density=True, edgecolor='green', alpha=0.7, label='Frecvențe relative')
    title(f"Distribuția pozițiilor finale")
    grid()
    show()


def l5p1a(valori, probabilitati, n):
    x = []
    u = uniform.rvs(size = n)
    for i in range(n):
        pozitie = 0
        sum = probabilitati[0]
        while u[i] > sum:
            pozitie += 1
            sum += probabilitati[pozitie]
        x.append(valori[pozitie])
    return x

def l5p1b():
    valori, probabilitati = range(4), (0.46, 0.40, 0.10, 0.04)

    data = l5p1a(valori,probabilitati,1000)
    bin_edges = [i+0.5 for i in range(-1,4)]

    hist(data, bin_edges, density=True, width=0.85,color='red',
    edgecolor='black',alpha=0.6, label="frecventa relativa")

    distribution = dict([(k,probabilitati[k]) for k in range(4)])
    bar(distribution.keys(),distribution.values(), width=0.85,color='blue',  
    edgecolor='black',alpha=0.6, label='probabilitati')

    xticks(ticks=range(4),labels=("0","A","B","AB"))
    yticks([k/100 for k in range(0,55,5)])
    legend(loc='upper right')
    grid()
    show()

def l5p2a(alpha, n):
    u = uniform.rvs(size = n)
    x = [-1/alpha*log(1-u[i]) for i in range(n)]
    return x

def l5p2b():
    alpha = 1/12

    data = l5p2a(alpha, 2000)

    probabilitate = sum([x > 5 for x in data]) / len(data)
    print(probabilitate)

    hist(data, 12, density=True, range=(0,60))
    x = range(60)
    plot(x,expon.pdf(x,loc=0,scale=1/alpha),'r-')

    xticks(range(0,60,5))
    grid()
    show()

def l6p1():
    data = norm.rvs(loc=165, scale=10, size=5000)

    hist(data, 14, density=True, color="green", range=(130,200),label='frecvențe relative')

    x = numpy.linspace(130,200,1000)
    plot(x, norm.pdf(x, loc = 165, scale = 10), '-r', label='funcția de densitate')
    xticks(range(130,200,5))
    legend(loc='upper right')

    media = data.mean()
    deviatia = data.std()
    print(media, deviatia)

    proportie = (numpy.sum((data >= 160) & (data <= 170))) / 5000
    print(proportie)
    #mean((data >= 160) and (data <= 170))

    grid()
    show()


def mers_aleator_axa(nr_pasi,p):          #  p: probabilitatea ca punctul să se deplaseze spre dreapta la fiecare pas.
    pozitii = [0]                         #  pozitii este o listă în care se stochează poziția punctului la fiecare pas.
    for _ in range(nr_pasi):       
        x = bernoulli.rvs(p)              #  bernoulli.rvs(p) returnează o valoare aleatoare x, care poate fi 1 cu probabilitatea p (pas la dreapta) sau 0 cu probabilitatea 1 - p (pas la stânga).
        pas = 2 * x - 1                   #  Folosim x pentru a defini variabila pas: Dacă x este 1, pas = 2 * 1 - 1 = +1 (deplasare la dreapta). Dacă x este 0, pas = 2 * 0 - 1 = -1 (deplaseaza la stânga).
        pozitii.append(pozitii[-1]+pas)   #  pozitii[-1] este ultima poziție a punctului.  pozitii[-1] + pas calculează noua poziție a punctului după ce face pasul respectiv.
    return pozitii

def sim_mers_aleator_axa(nr_pasi,p,nr_sim=1000):
    pozitii_finale = [mers_aleator_axa(nr_pasi,p)[-1] for _ in range(nr_sim)]
    
    bin_edges = [i+0.5 for i in range(-nr_pasi-1,nr_pasi+1)]
    hist(pozitii_finale, bin_edges, density = True, rwidth=0.9, align = 'mid',
      edgecolor='black', color = 'green', alpha = 0.3, label='frecvente relative')
    
    distributie = dict([(k-(nr_pasi-k), binom.pmf(k,nr_pasi,p))
                                                  for k in range(nr_pasi+1)])
    bar(distributie.keys(), distributie.values(), width=0.6, align='center',
            edgecolor='black', color='red', alpha=0.5, label='probabilitati')
    
    xticks(range(-nr_pasi,nr_pasi+1))
    legend(loc='upper left')
    grid()
    show()


sim_mers_aleator_axa(10, 0.5)