# PRINCIPLES OF PROGRAMMING LANGUAGES

This directory contains coding exercises done in HASKELL and PROLOG programming languages.
if you want to test these please download the Intepreters from:
- [Haskell]( https://www.haskell.org/hugs/pages/downloading-May2006.htm)
-  [Prolog](http://www.gprolog.org/)
---

## Lab1.hs (HASKELL)
### Exercise_1 
  Script that counts the total number of an Integer's digits that are 0, 3, 6 or 9.

### Exercise_2
  Function that has arguments of 3 positive ints and finds the k-greatest common divider. If the 3 ints have less that k common dividers then Zero is returned.
  
### Exercise_3
  Function that prints the number of seconds passed from the start of the year up until a certain moment in the same year.
  The arguments of this function are as follows
  $ seconds (MONTH, DAY) (HOUR, MINUTE, SECOND)
  If the arguments are incorrect then -1 is returned.
  
### Exercise_4
  Higher class Haskell function that as arguments has a fucntion and 2 variables and returns the summary of the function.
  
---
  
## Lab2.hs (HASKELL)
### Exercise_1
  Function that takes an integer list as arguments and an integer n and return the position of the list that contains the element with the lowest distance from n. Distance as in absolute value of 2 numbers substraction.
  
### Exercise_2
  Function that has a list of integers and a positive integer k as arguments and returns the list of integers, but modified as follows:
  For every element in the list we replace it with the average of the k next elements including itself. 
  For example in a 10 element list, and for k = 3, the first element will be the average of the 1st, 2nd and 3rd element of the list.
  
### Exercise_3
  Function that swaps the words in text. 
  For example "one two tree for" -> "two one tree for",  "You are beatiful" -> "are You beatiful"
  
### Exercise_4
  Function that takes as arguments a list of integers and a function f. Each i-element of the list is passed through the function f, in other words each element of the list is modified to be f(List[i],i)
  
---
  
## Lab3.pro (PROLOG)
### Exercise_1
  Facts:
  - hosted(I,Y,C): i-th Olympic games played at city C at year Y.
  - coutnry(C,D): city C is in country D.
  - continent(D,E): country D is in continent E.\
  - dif(T1,T2): term T1 is different from T2.
  
  Rules:
  - twice(C,N): city C has organised Olympic games twice with at least N years in between events.
  - in2Cities(D): coutnry D has organised Olympic games in 2 different cities.
  - in3Continents(I): the I-th , (I+1)-th and (I+2)-th Olympic games have been played at 3 different continents.
 
### Exercise_3
  Calculates the following function f, where m,n > 0:
  
  ![Untitled](https://user-images.githubusercontent.com/91612373/205694385-75ad1c3e-04b9-456f-a661-c91e2517006d.png)
  
### Exercise_4
  rule p(N) where is true only N is a positive integer and is not divided by any square of a prime number.
  
  ---
## Lab4.pro (PROLOG)
### Exercise_1
  Constructs a list with the n first number being the terms of a numerical progress. In detail the function have 4 arguments, the numnbers N, M and K and is true when the following list can be contstructed [m, m + k, . . . , m + (n − 1) · k]
  
### Exercise_2
  Finds the position of 2 lists that contain equal numbers.
  common(X,Y,L): where X,Y the 2 lists and L the empty list that is returned.
  
### Exercise_3
  Counts the unique elements in a list and return the times_of_occurence*element.
  
### Exercise_4
  In a list of positive integers L= [x1, x2, . . . , xn]  we count the summary of pairs (xi, xj), with 1 ≤ i < j ≤ n, for which one of the following must be true:
  - xi > 2xj 
  - xj > 2xi.
