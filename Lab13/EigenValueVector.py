import numpy as np

m = np.array([[1, 2],
              [2, 3]])

m,v =  np.linalg.eig(m)

print(m)
print(v)
