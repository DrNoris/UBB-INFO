# from scipy.stats import uniform
# from matplotlib.pyplot import bar, show, hist, grid, legend, xticks, yticks

# def p1(x, p, n):
#     v = []
#     for _ in range(n):
#         fx = uniform.rvs()
#         interval = x[0]
#         s = 0
#         for j in range(len(p)):
#             if fx > s:
#                 interval = x[j]
#             s += p[j]
#         v.append(interval)
#     return v

# x = [1, 2, 3, 4, 5, 6]  
# p = [1/6, 1/6, 1/6, 1/6, 1/6, 1/6] 
# n = 10000

# valori_generate = p1(x, p, n)

# frecvente_relative = [valori_generate.count(val) / n for val in x]

# bar(x, frecvente_relative, alpha=0.7, label='Frecvențe observate')
# bar(x, p, alpha=0.7, width=0.4, label='Probabilități teoretice')
# grid(True)
# legend()
# # xticks(range(len(x)), x)
# # yticks([i / 10 for i in range(11)])
# show()

from scipy.stats import uniform
from math import log
from matplotlib.pyplot import show, hist, grid, legend, xticks, plot
from scipy.stats import expon

def p1(alpha):
    u = uniform.rvs()
    t = -1/alpha * log(1 - u)

    return t


alpha = 1/12
data = [p1(alpha) for _ in range(10000)]

hist(data, bins=60, density = True,range=(0,60))
x = range(60)
plot(x, expon.pdf(x,loc=0,scale=1/alpha),'r-')
xticks(range(0,60,1))
grid()
show()

print (sum(x > 5 for x in data) / len(10000))