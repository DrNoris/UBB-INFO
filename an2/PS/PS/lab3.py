from random import choices, sample
from math import comb, perm

# #punctul 1
# def urna(n = 10000):
#     cnt_a = 0
#     cnt_a_b = 0

#     while n:
#         n = n - 1
#         bile = "rrrrraaavv"
#         extrase = sample(bile, 3)
#         if "r" in extrase:
#             cnt_a = cnt_a + 1
#             if extrase[0] == extrase[1] and extrase[1] == extrase [2]:
#                 cnt_a_b = cnt_a_b + 1

#     return cnt_a_b / cnt_a

# print(urna())

# #punctul 2
# from random import randrange
# from matplotlib.pyplot import bar, hist, grid, show, legend

# data = [randrange(1,7) for _ in range(5000)]
# bin_edges = [k+0.5 for k in range(7)]
# hist(data, bin_edges, density = True, rwidth = 0.9, color = 'green', edgecolor = 'black',
# alpha = 0.5, label = 'frecvente relative')


# distribution = dict([(i,1/6) for i in range(1,7)])
# bar(distribution.keys(), distribution.values(), width = 0.85, color = 'red', edgecolor = 'black',
# alpha= 0.6, label = 'probabilitati')
# legend(loc = 'lower left')
# grid()
# show()

# #punctul 3
from matplotlib.pyplot import bar, hist, grid, show, legend
from scipy.stats import binom
data = binom.rvs(n = 5, p = 6/10, size = 1000)
bin_edges = [k+0.5 for k in range(-1, 6)]
hist(data, bin_edges, density = True, rwidth = 0.9, color = 'green', edgecolor = 'black',
alpha = 0.5, label = 'frecvente relative')

distribution = dict([(k, binom.pmf(k, n=5, p = 6/10)) for k in range(6)])
bar(distribution.keys(), distribution.values(), width = 0.85, color = 'red', edgecolor = 'black',
alpha= 0.6, label = 'probabilitati')
legend(loc = 'lower left')

grid()
show()


