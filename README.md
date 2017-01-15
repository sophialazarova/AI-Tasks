# AI-Tasks
Homeworks solved during the AI course in Sofia University.


## Frogs Puzzle

### Description:
Game board consists of 2N+1 fields. There are N frogs on each side facing each other. Each frog squad should reach the opposite side of the board (Frogs can move only forward).

Allowed moves:

* 1 step forward, if there is an empty space.
* 2 steps forward (jumping over a frog from the opposite squad and landing on empty space)

Example:

\>>_<<

\>_><<

\><>_<

\><><_

\><_<> 

\_<><> 

\<_><> 

\<<>_> 

\<<_>>

## Sliding Blocks Puzzle
###Descriptiton
Place in correct order a NxN board with scrambled numbers from 0 to N-1. Check [this]({http://mypuzzle.org/sliding}) implementation of the game to get idea of it's rules. 

The program finds a solution to a scrambled board with the A* algorithm using Manhattan distance as heuristic function.

## N Queens Problem
The N Queens promblem implemented with the min conflicts algorithm.

Example:
Solution for 5 queens:

\#	 #   #   Q   # 

\#   Q   #   #   # 

\#   #   #   #   Q 

\#   #   Q   #   # 

Q    #   #   #   # 


## Tic Tac Toe Game
Tic Tac Toe game - player against the computer.
The computer makes his moves based on the MiniMax algorithm with Alpha-Beta pruning. 

## Classification using Knn

### Description 
Using the Knn algorithm to predict the iris class of the next given set of plant parameters.
The data set used for implementation is the [Iris Data set](http://archive.ics.uci.edu/ml/datasets/Iris) 