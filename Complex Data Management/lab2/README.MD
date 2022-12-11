# Lab 2 Spatial Data

---

## Part 1 (R-Tree construction via Bulk Loading):

<p> 
program which constructs an R-Tree for a set
of polygons, which we read from two text files.
The program calculates the MBRs of the objects and then bulk
loads the tree after sorting the MBRs using a space filling curve, more specifically the z-order. These curves are functions, which we map a multidimensional vector to a number in one dimension
space. Two objects that are close spatially have a high probability of
are matched to nearby values. By extension, two MBRs that are close to the site will
with a high probability of entering the same leaf of the tree.

coords.txt and offsets.txt are the input files. The first contains
coordinates of points in the form <x>,<y>. The second contains records of the format
<id>,<startOffset>,<endOffset> where id is the unique identifier of a polygon
object and startOffset (respectively endOffset) is the no. line in the coords.txt file
where the coordinates of the points that form the start (respectively end).
every object.

The program writes to the output file Rtree.txt all its nodes
tree in order of their ids. The format of each line is:
[isnonleaf, node-id, [[id1, MBR1], [id2, MBR2], ..., [idn, MBRn]]]
where isnonleaf=1 if the node is not a leaf (isnonleaf=0 if it is a leaf), node-id is the id
of the node followed by the records within the node. In each record, the id is either
a node-id (if the record points to a node) or an object-id if the record points to
object. The MBR, of type [x-low, x-high, y-low, y-high], is either the MBR of a node (if
isnonleaf=1) or of an object (if isnonleaf=0).
</p> 

---

## Part 2 (Range queries):

<p> 
Range query valuation function in the R-Tree we built. The range
of the question is defined by a rectangle W and the question is to find the MBRs
that intersect W. So your function will take as an argument the nodeid of the root of R-
tree, the query-rectangle W in the form [x-low, x-high, y-low, y-high] and will calculate the
query results using R-tree. To test your function
given the file Rqueries.txt containing questions of the form
<x_low> <y_low> <x_high> <y_high>
Where x-low is the lower bound of the x dimension of the query window W and x-high the upper bound
limit. Likewise for the y dimension. For each question print the ids of their objects
whose MBR overlaps with the query window W (filter step).
</p> 

#### Development details

<p> 
Program takes as command-line arguments its file
R-tree created in Part 1 and the query file Rqueries.txt. It 
builds the tree from the contents of the R-tree file and then read
and run the questions sequentially from a question file. For each question we
print to the output the line of the question (starting from line 0), the number of
results in parentheses, and the ids of the results, i.e. the objects that
they go through the filter step

The function checkMBR(W,mbr): checks if the mbr satisfies the query
window W with the filter step method. Below is a graphic representation of it
rationale for the condition that checks if mbr is "inside" window W:

![image](https://user-images.githubusercontent.com/91612373/206917572-aba7457f-8819-4d92-8996-7abb6516d806.png)

In the event that none of the conditions apply, the shape is inside W.

goDeeper(n_id,W,result): takes as arguments, a node ID from which to start the
access to the lower levels of the tree following the search rules with the help of
checkMBR. The table result holds the results we find in the leaves of the tree.

In the end, the questions found in the file Rqueries.txt are evaluated. First it creates the
window W and a dictionary whose keys are the IDs of the nodes inside the root and as
their prices their mbr. For each pair in the dictionary, if it satisfies the checkMBR query, then we call goDeeper
to find whether the leaves belonging to the node also satisfy the W query.
</p> 

---

## Part 3 (kNN queries):

<p> 
Implementation of the best-first search algorithm to retrieve the closest MBR object to one
reference point q. The version of the algorithm is the one that does incremental
NN search and uses a priority queue to organize the closest R-trees
node entries seen in a priority queue. The priority queue
includes node MBRs but also object MBRs. When an MBR object is dequeued
is also the next closest MBR to q.
</p> 

#### Development details

<p> 
Program which takes as an argument the id of the root of the tree
and calculates and prints the k closest object-ids to a point q=(x,y).
The program takes as command-line arguments its file
R-tree created in Part 1, the questions fileNNqueries.txt and the no
k (no. of nearest neighbors). It creates the tree from its contents
R-tree file and then reads and executes the queries sequentially from file
questions. For each question it prints the line of the question to the output
(starting from line 0), and the ids of the results, i.e. the k closest ones
object MBRs to the point of the question
</p> 
