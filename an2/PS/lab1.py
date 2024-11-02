# # # from random import sample
# # # from math import factorial
# # # from itertools import permutations

# # # a = permutations("word")
# # # total = 0

# # # print("Permutarile cuvantului word:")
# # # for perm in a:
# # #     newWord = ''.join(perm)
# # #     print(newWord)
# # #     total = total + 1

# # # print("Numarul de permutari este:" + str(total))

# # # print(sample("word", 4))

# # from random import sample
# # from math import perm, comb
# # from itertools import permutations, combinations
# # import random

# # def aranjamente(word, numar, numar_total = False, aleator = False):
# #     if numar_total == True:
# #         a = perm(len(word), numar)
# #         print(a)
# #         return
    
# #     if aleator == True:
# #         print(sample(word, numar))
# #         return

# #     for aranj in permutations(word, numar):
# #         print(aranj)

# # # aranjamente("word", 2)
# # # aranjamente("word", 2, numar_total=True)
# # # aranjamente("word", 2, aleator=True)

# # def combinari(word, numar, numar_total = False, aleator = False):
# #     if numar_total == True:
# #         a = comb(len(word), numar)
# #         print(a)
# #         return
    
# #     if aleator == True:
# #         lungime = len(word)
# #         inceput = 0
# #         numarCopie = numar

# #         for i in range(numar):
# #             index = random.randint(inceput, lungime - numarCopie)
# #             inceput = index + 1
# #             numarCopie = numarCopie - 1
# #             print(word[index])
# #         return
    
# #     for com in combinations(word, numar):
# #         print(com)

# # combinari("word", 2)
# # combinari("word", 2, numar_total=True)
# # combinari("word", 2, aleator=True)

# from itertools import combinations_with_replacement

# l = list(combinations_with_replacement("ABCDE", 4))
# print(l)

from itertools import permutations

a = [1,1,1,1,1,0,0,0,0,0,0,0]

asezari = permutations(list(a), 12)
total = 0

print (total)
for asezare in asezari:
    anterior = -1
    for i in asezare:
        if anterior == -1:
            anterior = i
        else: 
            if anterior == i:
                ok = 0
                break
            else:
                anterior = i
    total = total + ok

total = total * 5 * 4 * 3 * 2 * 1

print (total)
