#!/usr/bin/env python
# coding: utf-8

# Aggelos Dimokas 2683
import csv

def merge(myList):
    myNewList = []
    if len(myList) > 1:
        mid = len(myList) // 2
        left = myList[:mid]
        right = myList[mid:]

        # Recursive call on each half
        left = merge(left)
        right = merge(right)

        # three iterators for traversing the two halves and main list
        i = j = k = 0
        
        while i < len(left) and j < len(right):
            
            if left[i][0] == right[j][0]:
                myNewList.insert(k,[left[i][0], str(int(left[i][1])+ int(right[j][1]))])
                i += 1
                j += 1
                
            elif left[i][0] < right[j][0]:
                # The value from the left half has been used
                myNewList.insert(k,left[i])
                # Move the iterator forward
                i += 1
                
            else:
                myNewList.insert(k,right[j])
                j += 1
            
            k += 1
               

        # For all the remaining values
        while i < len(left):
            myNewList.insert(k,left[i])
            k += 1
            i += 1

        while j < len(right):
            myNewList.insert(k,right[j])
            k += 1
            j += 1
            
    else:
        return myList
    
    return myNewList
    
          
            
with open("R.tsv") as R:
    rd = list(csv.reader(R, delimiter="\t"))
    
rd = merge(rd)


    
with open('Rgroupby.tsv', 'w', newline='', encoding='utf-8') as out:
    tsv_writer = csv.writer(out, delimiter='\t')
    for row in rd:
        tsv_writer.writerow(row)
    

