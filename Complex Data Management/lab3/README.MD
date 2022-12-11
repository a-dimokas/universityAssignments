# Lab 3 Spatial networks and top-x queries

The two input files that build up the network are in the data directory named edge.txt and node.txt. The out.txt file is the output of the program.

---

## Part 1 (Memory storage and indexing):

<p> 
Program that reads the data from the given files and 
constructs a structure in memory for it. Specifically, for each node, the structure
stores (1) its coordinates and (2) in an adjacency list
its neighbors in the network (which imply the existence of the corresponding edges). The
 structure is of type array in which the position of a node implies the
Its ID. E.g. the node with Node-ID=0 is at position 0 of the structure. For every neighbor
stores in our structure the Euclidean distance (L2 Distance) from the node to
neighbor given in the file. Then writes them to an output file named out.txt
structure data.
</p> 

#### Development details

<p> 
**edgeArray** -> An array that contains the edges of the network.
**edgeDict** -> adjacency table of the edges, to find the neighboors of a node easily.
</p> 

---

## Part 2 (Dijkstra and A*):

<p> 
Ιmplemented the Dijkstra and A* shortest path algorithms. The
functions accepts as input (a) the identifier of a source node and
(b) the identifier of a terminal node (target node) and they calculate correctly
and return (in list form) the shortest path from the original to
terminal node and the corresponding network distance (i.e. the distance of the shortest
path). Also, the number of iterations (ie
of node visits) that take place when the algorithm is printed.

To calculate the lower bound of the network distance between 2 unconnected nodes
which A* uses, we calculate the Euclidean distance based on the coordinates of
of nodes, while for the distance of an edge we use the ready values that exist
in our structure.
</p> 

#### Development details


<p> 
The readGraph() function reads the data we stored in the
out.txt file and modifies them to be in the appropriate structures

The function djikstra(s,t) calculates based on the Djikstra's algorithm the path with the minimum distance from
node s to node t. We initialize the structures, and then for each node
in the graph we initialize the minimum distance from the initial node to
infinity.
We start with node s by adding it to the priority queue. Then, iteratively, we pull out of the queue
prioritizing the first node and check its neighboring nodes by putting them in the queue as well,
depending on the distance they are from the original node, the nodes that
are closer to the original have priority.
The function ends when the node we remove from the queue is also our final node, if the network does not have
 a path from s to t then the function
returns the corresponding message.

The alphaStar(s,t) function calculates with the Alpha star algorithm the path with the minimum distance from
node s to node t. It shares many variable names with the previous function. Some
notable changes however are the following:
We measure the Euclidean distance of each node from the terminal node t and store the values in
table dist. We use the value of the equivalent
element in the dist array when adding to the queue
priority as it should work in the
algorithm A*.


Finally, the program takes as arguments 2 integers, the source and target nodes for
which it runs the djikstra and alphaStar functions and prints the results
their.


</p> 

---
