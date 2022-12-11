# Lab 1 Implementation of Operators

---

## Part 1 (merge-join):

<p> 
Program that reads the files R_sorted.tsv and S_sorted.tsv, calculates and
writes to a file RjoinS.tsv the join of R and S, considering that they have in common
their first field only. 
</p> 

#### Development details

<p> 
Initially the flag variable is to know when we need to check if the tuple read
previously from array R has the same first field as the one you just read.
In the while we have an if case that checks if the fields A of R and S are equal. If the fields are equal, then we pass
the tuple (of S) into a dictionary buffer for later use. Then we read the next one
tuple of S. If again the fields A of R and S are the same then we also add this tuple of S to the buffer, until
to find a tuple of S that does not have the same A domain as that of R.
If the fields A are not the same, we go to else and then we check if the flag variable has changed and keep it
variable bufferMax the maximum size of the buffer dictionary, and we check if again A of R is the same as that
we had previously, so that we rewrite the Bs of S that we have saved in the dictionary as join tuples with B
of R. Otherwise if we have a different A, we reset the flag again and empty the buffer.
Finally we check if A of R is less than A of S and if the reading of R is not finished then
we read the next tuple from R, else we read the next from S.
Also printed is the bufferMax variable which holds the maximum size the buffer dictionary has reached
execution of the program.
</p> 

---

## Part 2 (union):

<p> 
Program which reads the files R_sorted.tsv and S_sorted.tsv, calculates and
writes to a RunionS.tsv file the union of R and S, assuming that they have exactly
same fields. The program only need to read the file lines once
R_sorted.tsv and S_sorted.tsv and at the same time calculates their union and write the
union tuples in the output file, implementing a variant of the merge-join algorithm. The use of buffers was not implemented.
</p> 

#### Development details

<p> 
Used flags to stop the repetition in time
Whenever it tries to read from R or S, if it has no other tuple for
to read then the corresponding flag variable becomes True and exits the while.
Inside the while, checks are made (with if cases) regarding whether we have the same tuples in R and S we write
one of the 2 and skip the other to avoid printing a duplicate tuple. Also if the
tuples are not the same then the smaller of the 2 tuples read from the R and S files is written.
When the iteration is finished the remaining tuples from the missing file must be added
finished yet. That's why after the While there are 2 if cases that check which of the 2 files has others
tuples to be read and ends up passing through the RunionS.tsv file.
Finally to check if a tuple to be written to the RunionS.tsv file is the same as this one
which was just written in the previous iteration, there is the variable prev. So that we avoid it
creating duplicates on output
</p> 

---

## Part 3 (intersection):

<p> 
Program which reads the files R_sorted.tsv and S_sorted.tsv and calculates and
writes to a file RintersectionS.tsv the intersection of R and S, considering that they have
exactly the same fields. The program will only need to read their lines once
files R_sorted.tsv and S_sorted.tsv and at the same time calculate their intersection and write the
tuples of the intersection in the output file, implementing a variant of the merge-join algorithm. The use of buffers was not implemented.
</p> 

#### Development details

<p> 
Logic stays the same as with the previous 2 parts
</p> 

---

## Part 4 (set-difference):

<p> 
Same as before for the set-difference operator
</p> 

---

## Part 5 (Grouping and aggregation):

<p> 
Same as before for the Grouping and aggregation operators
</p> 


#### Development details

<p> 
Function merge(list) is responsible for the
return of the array list after merge-sort.
The difference with the classic merge-sort algorithm is that the operation of adding to the second
field when the 2 tuples have same first field and also merge these tuples into one.
The method returns the list myNewList which we have merged-sorted from the original list
which we fed into the function-method.
</p> 

