# AUTHOR: Angelos Dimokas 2683
import sys

RtreeFile = open(sys.argv[1], "r")
Rqueries = open(sys.argv[2], "r")

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
######### R contains Rtree

def checkMBR(W,mbr):
    #checks if an mbr is eligible by filter step for window W
    if(W[3]<mbr[2] or W[2]>mbr[3] or W[1]<mbr[0] or W[0]>mbr[1]):
        return False
    else:
        return True


def goDeeper(n_id,W,result):

    if(R[n_id][0]==0):
        for i in R[n_id][2]:
            if(checkMBR(W,i[1])):
                result.append(i[0])
    else:
        for i in R[n_id][2]:
            if(checkMBR(W,i[1])):
                goDeeper(i[0],W,result)


output = []
counterQ = 0

for quest in Rqueries:
    results = []
    result = 0

    x_low, y_low, x_high, y_high = quest.split(' ')
    x_low, y_low, x_high, y_high = float(x_low), float(y_low), float(x_high), float(y_high)
    W = [x_low,x_high,y_low,y_high]
    

    #key = node_id , value = mbr
    mbroot = {}
    for i in R[len(R)-1][2]:
        mbroot[i[0]] = i[1]
    
    
    #remember where you need to go
    for i in mbroot:
        if(checkMBR(W,mbroot[i])):
            
            goDeeper(i,W,results)
    
    strResu = ''
    for i in results:
        strResu += str(i) + ', '
    strResu = strResu[:len(strResu)-2]
    
    output.append(str(counterQ) + ' (' + str(len(results)) + '): ' + strResu)
    counterQ +=1


out = sys.stdout
for i in output:
    out.write(i+'\n')




  
RtreeFile.close()
Rqueries.close()