# PRINCIPLES OF PROGRAMMING LANGUAGES

This directory contains coding exercises done in HASKELL and PROLOG programming languages.
if you want to test these please download the Haskell Intepreter from:
- this link ( https://www.haskell.org/hugs/pages/downloading-May2006.htm )
- and this link ( http://www.gprolog.org/ )

Each lab file contains 4 exercises in progressive difficulty.

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
  
  
## Lab2.hs
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
  
