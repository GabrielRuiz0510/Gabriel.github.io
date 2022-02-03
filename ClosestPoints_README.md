# Gaberiel.github.io
calculates the closest point among one million points on the 2D plane using spatial hashing. 
The file points.txt provides the given points. They are described as (x, y) coordinates.

Each x and y lies between [0, 1]

We calculate using the formula sqrt((x2 − x1)^2 + (y2 − y1)^2)

We store each point in a 2D hashtable made of Nodes
We use prime number modulo to minimize collisions
We compare each point in its grid cell and neighbouring grid cells and always maintaining minimum distance we have computed so far.
