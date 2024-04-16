import numpy as np
import matplotlib.pyplot as plt

N=1000000
sx = np.zeros(N)
sy = np.zeros(N)

for i in range(1, N):
    k = np.random.randint(1,4)
    sx[i] = sx[i-1]/2 + k-1
    sy[i] = sy[i-1]/2
    if k==2:
        sy[i] += 2

plt.plot(sx, sy, 'k.', markersize=1)
plt.title('Sierpinski triange')
plt.axis('off')
plt.show()

