#!/usr/bin/env python
# coding: utf-8


# Aggelos Dimokas 
import csv

with open("R_sorted.tsv") as R:
    with open("S_sorted.tsv") as S:
        with open('RunionS.tsv', 'w', newline='', encoding='utf-8') as out:
            
            rd = csv.reader(R, delimiter="\t")
            sd = csv.reader(S, delimiter="\t")
            tsv_writer = csv.writer(out, delimiter='\t')
            
            r_line=next(rd)
            s_line=next(sd)
            
            # gia na elegxoume me to proigoumeno stoixeio pou vazoume sto out
            prev=['  ', '  '] # eksaleifoume diplotypa stin eksodo
            
            #ginontai True otan teleiosoun oi grammes
            flagR = False   #tou R
            flagS = False   #tou S
            
            while not(flagR or flagS):
                if(r_line[0] == s_line[0]):
                    
                    if(r_line[1] > s_line[1]):
                        if(s_line != prev):
                            tsv_writer.writerow([s_line[0],s_line[1]])
                            prev = s_line 
                        try:
                            s_line=next(sd)
                        except StopIteration:
                            flagS = True
                            
                    elif(r_line[1] < s_line[1]):
                        if(r_line != prev):
                            tsv_writer.writerow([r_line[0],r_line[1]])
                            prev = r_line
                        try:
                            r_line=next(rd)
                        except StopIteration:
                            flagR = True
                    
                    else:
                        if(s_line != prev):
                            tsv_writer.writerow([s_line[0],s_line[1]])
                            prev = s_line
                        try:
                            s_line=next(sd)
                        except StopIteration:
                            flagS = True
                        try:
                            r_line=next(rd)
                        except StopIteration:
                            flagR = True
                
                elif(r_line[0] < s_line[0]):
                    if(r_line != prev):
                        tsv_writer.writerow([r_line[0],r_line[1]])
                        prev = r_line
                    try:
                        r_line=next(rd)
                    except StopIteration:
                        flagR = True
                    
                else:
                    if(s_line != prev):
                        tsv_writer.writerow([s_line[0],s_line[1]])
                        prev = s_line
                    try:
                        s_line=next(sd)
                    except StopIteration:
                        flagS = True
           
            if(not flagS):
                while True:
                    if(s_line != prev):
                        tsv_writer.writerow([s_line[0],s_line[1]])
                        prev = s_line
                    try:
                        s_line=next(sd)
                    except StopIteration:
                        break
            if(not flagR):
                while True:
                    if(r_line != prev):
                        tsv_writer.writerow([r_line[0],r_line[1]])
                        prev = r_line
                    try:
                        r_line=next(rd)
                    except StopIteration:
                        break
                



