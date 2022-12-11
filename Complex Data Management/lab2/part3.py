# AUTHOR: Angelos Dimokas 2683
import heapq
from math import sqrt
import sys


#read as arguments !
RtreeFile = open(sys.argv[1], "r")
ΝΝqueries = open(sys.argv[2], "r")
k = int(sys.argv[3])

#we read the Rtree
R = []
for i in RtreeFile:
    isnonleaf = int(i[1])
    node_id = int(i.split(',')[1])
    mbr=[]
    flag = 0
    temp = ''
    for x in i:
        if(x=='['):
            flag +=1
        if(flag>=2):
            temp += x
    if(temp[:2]=='[['):

        tempid = ''
        tempmbr = []
        for x in temp[2:len(temp)-4]:
        
            if(x!=',' and x !='[' and x !=']' and x!=' '):
                tempid+=x

            else:
                if('.' not in tempid and len(tempid)>0):
                    mbr.append([int(tempid),[]])

                elif(len(tempid)>0):
                    mbr[len(mbr)-1][1].append(float(tempid))

                tempid = ''

    else:
        for i in temp[1:len(temp)-3].split(','):
            mbr.append(float(i))

    R.append([isnonleaf,node_id,mbr])
#R contains Rtree


RtreeFile.close()

#point[0]=x, [1]=y
def dist(point,mbr):
    dx = 0.0
    if(point[0]<mbr[0]):
        dx = mbr[0] - point[0]
    elif(point[0]>mbr[1]):
        dx = mbr[1] - point[0]
    
    dy = 0.0
    if(point[1]<mbr[2]):
        dy = mbr[2] - point[1]
    elif(point[1]>mbr[3]):
        dy = mbr[3] - point[1]

    return sqrt(dx**2 + dy**2)
    


def goDeeper(x, y, k, res, Q):
    #if id = -1 then is a leaf so we just save,pop and continue
    #using the third item in the tuple as the REAL obj id
    if(Q[0][1]==-1):
        res.append(heapq.heappop(Q)[2])
        k -= 1

    elif(R[Q[0][1]][0]==0):
        for i in R[Q[0][1]][2]:
            Q.append((dist([x,y],i[1]),-1,i[0]))
        heapq.heappop(Q)
        
    else:
        for i in R[Q[0][1]][2]:
            Q.append((dist([x,y],i[1]),i[0]))
        heapq.heappop(Q)

    heapq.heapify(Q)
    if(k>0):
        goDeeper(x,y,k,res,Q)

        



results = []
for query in ΝΝqueries:
    x, y = query.split(' ')
    x = float(x)
    y = float(y)

    Q = [] 
    # [(dist1,n_id1),(dist2,n_id2)...]
    for i in R[len(R)-1][2]:
        Q.append((dist([x,y],i[1]),i[0]))
    
    heapq.heapify(Q)
    
    result = []
    
    goDeeper(x, y, k, result, Q)
    results.append(result)

ΝΝqueries.close()

counter = 0
out = sys.stdout
for i in results:
    out.write(str(counter) +  ': ' + str(i)[1:-1] + '\n')
    counter += 1




