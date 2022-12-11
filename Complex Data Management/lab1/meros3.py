#!/usr/bin/env python
# coding: utf-8

# Aggelos Dimokas 2683
import csv

with open("R_sorted.tsv") as R:
    with open("S_sorted.tsv") as S:
        with open('RintersectionS.tsv', 'w', newline='', encoding='utf-8') as out:
            
            rd = csv.reader(R, delimiter="\t")
            sd = csv.reader(S, delimiter="\t")
            tsv_writer = csv.writer(out, delimiter='\t')
            
            r_line=next(rd)
            s_line=next(sd)
            
            prev=['  ', '  '] # eksaleifoume diplotypa stin eksodo
            
            while True:

                if(r_line[0] == s_line[0] and r_line[1] == s_line[1]):
                    if(r_line != prev):
                        tsv_writer.writerow([r_line[0],r_line[1]])
                        prev = r_line
                    try:
                        r_line = next(rd)
                    except StopIteration:
                        break
                    try:
                        s_line = next(sd)
                    except StopIteration:
                        break

                if(r_line[0] == s_line[0]):

                    if(r_line[1] > s_line[1]):
                        try:
                            s_line = next(sd)
                        except StopIteration:
                            break

                    if(r_line[1] < s_line[1]):
                        try:
                            r_line = next(rd)
                        except StopIteration:
                            break

                if(r_line[0] < s_line[0]):
                    try:
                        r_line = next(rd)
                    except StopIteration:
                        break

                if(r_line[0] > s_line[0]):
                    try:
                        s_line = next(sd)
                    except StopIteration:
                        break




