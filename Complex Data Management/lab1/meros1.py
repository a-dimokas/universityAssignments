#!/usr/bin/env python
# coding: utf-8


# Aggelos Dimokas 2683
import csv
buffer = {}
bufferMax = 0


with open("R_sorted.tsv") as R:
    with open("S_sorted.tsv") as S:
        with open('RjoinS.tsv', 'w', newline='', encoding='utf-8') as out:
            
            flag = 0

            rd = csv.reader(R, delimiter="\t")
            sd = csv.reader(S, delimiter="\t")
            tsv_writer = csv.writer(out, delimiter='\t')

            r_line=next(rd)
            s_line=next(sd)
            
            sBreak = True
            rBreak = True

            
            while (sBreak or rBreak):
                

                if(r_line[0] == s_line[0] and sBreak):
                    
                    flag = 1
                    prevS = s_line[0]
                    prevR = r_line[0]
                    
                    if r_line[0] in buffer:
                        buffer[r_line[0]].append(s_line[1])
                    else:
                        buffer[r_line[0]] = [s_line[1]]
                        
                    try:
                        s_line = next(sd)
                    except StopIteration:
                        sBreak = False

                else:

                    if(flag > 0):
                        if(len(buffer[prevR]) > bufferMax):
                            bufferMax = len(buffer[prevR])
                            
                        if(r_line[0] == prevR and rBreak):
                            for e in buffer[prevR]:
                                tsv_writer.writerow([r_line[0], r_line[1], e])
                            
                            
                        else:
                            flag = 0
                            buffer = {}
                            
                            
                            
                        
                    if(r_line[0] < s_line[0] and rBreak):
                        try:
                            r_line = next(rd)
                        except StopIteration:
                            rBreak = False
                    else:
                        try:
                            s_line = next(sd)
                        except StopIteration:
                            sBreak = False
print(bufferMax)            

