from matplotlib.pyplot import bar, hist, grid, show, legend
from scipy.stats import binom

n = 10
p = 0.5
data = [deplasare(n, p) [-1] for _ in range(1000)]

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