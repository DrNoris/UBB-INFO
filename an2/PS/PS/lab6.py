# # from scipy.stats import norm
# # from numpy import mean, std, linspace
# # from matplotlib.pyplot import show, hist, grid, legend, xticks, plot

# # data = norm.rvs(loc=165, scale=10, size=1000)
# # filtrare = [160 < x < 170 for x in data]
# # print(mean(filtrare))
# # hist(data, bins=14, density=True, alpha=0.6, color='blue', range=(130,200))

# # x = linspace(min(data), max(data), 1000)
# # plot(x, norm.pdf(x, loc=165, scale=10), 'r-', label='Distribuția normală')

# # print(mean(data))

# # grid(True)
# # legend()
# # show()

# from scipy.stats import expon, uniform
# from numpy import mean, std, multiply

# data = [(I < 0.4) * expon.rvs(scale = 5) + 
#  (I > 0.4) * uniform.rvs(loc = 4, scale = 2) for I in uniform.rvs(loc = 0, scale = 1, size = 1000)]

# filtrare = [x < 5 for x in data]
# print(mean(filtrare))

# teoretica = expon.cdf(5, scale = 5) * 0.4 + uniform.cdf(5, loc = 4, scale = 2) * 0.6
# print(teoretica)

from scipy.stats import uniform
from numpy import exp, mean
from scipy.integrate import quad

x = uniform.rvs(loc = -1, scale = 4, size = 1000)
g = lambda x : exp(-x**2)


print(mean(g(x)) * (3 - (-1)))
print(quad(g, -1, 3))