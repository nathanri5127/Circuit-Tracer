****************
* Circuit Tracer
* CS 221
* 05/19/2023
* Nathan Richardson
**************** 

OVERVIEW:

 Finds the path from a starting point to an end point on a constructed board with obstacles.


INCLUDED FILES:

 * CircuitTracer.java - Search algrithm for the board
 * CircuitBoard.java - Constructs the board from a inputted file
 * README - this file

ANALYSIS:
 1. Stacks only explore one route at a time due to it only taking in the last element inputted. A stack would be used with a preference
	for solutions rather than path length. A queue on the other hand explores each path at the same time as it takes in the first element inputted
	and not the last. This would be used to find the best path length.
 2. No, both storages will still have the same amount of paths as they will analyze all of the possible solutions.
 3. It depends on the circuit board. Generally, a stack has a stronger chance of brute forcing the path and finding a solution quick, but can be surpassed
	by a queue if the solution cant be brute forced easily.
 4. A queue would find the shortest solution as it tracks each possible path at the same time, so the first solution found would be the shortest.
 5. A queue would have a lower memory over time as it removes each path when its found while a stack has to find every path so memory would fluctuate.
 6. The runtime is o(n^2). This is determined through the number of pathways which would cause a exponential increase in runtime. The search algorithm has
	to determine if the path is invalid or valid and the size of the board, and has to depend on the size of the paths. Best case would be o(nlogn), but will
	usually be o(n^2).

PROGRAM DESIGN AND IMPORTANT CONCEPTS:

 CircuitBoard compiles and creates a board with a starting point and ending point with open and closed spaces. The board is constructed
	from a scanned file and exceptions are thrown depending on if the inputted board has a invalid board format. CircuitTracer takes the created
	board and traces a path from the starting point and ending point through the open spaces. The search algorithm uses both a stack and queue and 
	can be run through the command line. 

TESTING:

 My main source of testing was CircuitTracerTester, and using the command line for additional testing. The main bug remaning is
	not throwing an exception for some case tests for the command line format.


DISCUSSION:
 
 One part that was interesting was going back to FormatChecker and GridMoniter and seeing how much my code has changed over the time of the course.
	CircuitBoard is a more evolved version of both of those assignments showing how much ive learned. The biggest point of discussion was
	understanding how circuitTracer worked as it required knowledge of how both a stack and queue work in a search algorithm.
 


----------------------------------------------------------------------------