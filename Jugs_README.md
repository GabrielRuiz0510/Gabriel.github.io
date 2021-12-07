# Gaberiel.github.io
This program helps solve the Jugs problem using depth_first search.

It will take three numbers A,B,C from user
The program will calculate through recursion to solve this issue

It uses three different actions to solve this issue. fill one of the jugs to capacity,
emptying one of the jugs, or pouring into the other jug.

It uses memoization to reduce time by storing each visited state [A,B] with backpointers to allow printing of path.
The 2D array is implicit.

example given below

//Enter A: 3

Enter B: 4

Enter C: 5

Yay! Found a solution.

Fill Jug 1 [a = 3, b = 0]

Pour Jug 1 -> Jug 2 [a = 0, b = 3]

Fill Jug 1 [a = 3, b = 3]

Pour Jug 1 -> Jug 2 [a = 2, b = 4]

Empty Jug 2 [a = 2, b = 0]

Pour Jug 1 -> Jug 2 [a = 0, b = 2]

Fill Jug 1 [a = 3, b = 2]

Failed path:

Enter A: 3

Enter B: 6

Enter C: 5

Impossible!
