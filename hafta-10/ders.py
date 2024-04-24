import numpy as np
import random
import matplotlib.pyplot as plt

N = 100000
x = np.zeros(N+1)
y = np.zeros(N+1)

for i in range(1,N+1):
    k_arr = [1,2,3]
    k = random.choice(k_arr)
    x[i] = 0.5 * x[i-1] + k - 1
    y[i] = 0.5 * y[i-1]
    if k == 2:
        y[i] = y[i] + 2


plt.scatter(x,y,s=1) # dot boyutu s=1 ile set edildi.
plt.show()

# https://en.wikipedia.org/wiki/Sierpi%C5%84ski_triangle

