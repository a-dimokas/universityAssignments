# AUTHOR: Angelos Dimokas 2683
import sys
nodeFile = open(sys.argv[1], "r") #node.txt
edgeFile = open(sys.argv[2], "r") #edge.txt

results = []

edgeArray = []
for line in edgeFile:
    e_id, ns_id, nt_id, dist = line.split(' ')
    ns_id = int(ns_id)
    nt_id = int(nt_id)
    dist = float(dist)
    edgeArray.append([ns_id,nt_id,dist])
    edgeArray.append([nt_id,ns_id,dist])

edgeDict = {}
for i in edgeArray:
    if i[0] not in edgeDict:
        edgeDict[i[0]] = [i[1],i[2]]
    else:
        edgeDict[i[0]].append(i[1])
        edgeDict[i[0]].append(i[2])

for line in nodeFile:
    n_id, x, y = line.split(' ')
    n_id = int(n_id)
    x = float(x)
    y = float(y)

    neig = edgeDict.get(n_id)
    if(neig != None):
        results.append([n_id,x,y,neig])
    else:
        results.append([n_id,x,y,[]])

    


nodeFile.close()
edgeFile.close()

with open('out.txt', 'w') as out:
    for i in results:
        out.write(str(i[0])+' '+str(i[1])+' '+str(i[2])+' ')
        tmp = ''
        for j in i[3]:
            tmp += str(j) + ' '
        out.write(tmp + '\n')