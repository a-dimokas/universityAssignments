from pymorton import interleave_latlng
from math import ceil
import sys



def upperMBR(lowerMBR):
    
    x_list = []
    y_list = []
    
    for i in lowerMBR:
        x_list.append(i[0])
        x_list.append(i[1])
        y_list.append(i[2])
        y_list.append(i[3])
        
    x_low=min(x_list)
    x_high=max(x_list)
    y_low=min(y_list)
    y_high=max(y_list)
    
    return [x_low, x_high, y_low, y_high]



#responsible for root
def topLevel(lowerLevel, n_id, lvl):
    
    tmp_lvl = []
    for i in lowerLevel:
        
        tmpmbr = []
        for j in i[2]:
            tmpmbr.append(j[1])
        tmp_lvl.append([i[1],upperMBR(tmpmbr)])

    print('1 nodes at level', lvl)
    return [1,n_id,tmp_lvl]
    



#this functions serve (creates) the levels between leafs and root
def upperLevel(lowerLevel, n_id, lvl):
    
    
    this_level = []
    temp_level = []
    new_level = []
    
    #how many nodes we need to create
    nodes = ceil(len(lowerLevel)/20)
    
    group = 0
    temp = []
    last_leaf_size = len(lowerLevel)%20
    
    #riza
    if(nodes==1):
        this_level.append(topLevel(lowerLevel,n_id,lvl))
        
    

    #2 epipeda katw apo tin riza
    else:
        
        if(last_leaf_size>=8 or last_leaf_size==0):
            for i in lowerLevel:

                for j in i[2]:
                    temp.append(j[1])
                new_mbr = upperMBR(temp)
                temp_level.append([i[1],new_mbr])
                temp=[]
                group+=1

                if(group==20):
                    temp = []
                    group = 0
                    this_level.append([1,n_id,temp_level])
                    temp_level = []
                    n_id+=1
                
            if(group>0):
                this_level.append([1,n_id,temp_level])
                n_id+=1
                    
                    
        else:
        
            tmp = len(lowerLevel)-20-last_leaf_size
            for i in lowerLevel[:tmp]:

                for j in i[2]:
                    temp.append(j[1])
                new_mbr = upperMBR(temp)
                temp_level.append([i[1],new_mbr])
                temp=[]
                group+=1

                if(group==20):
                    temp = []
                    group = 0
                    this_level.append([1,n_id,temp_level])
                    temp_level = []
                    n_id+=1
            
            for i in lowerLevel[tmp:tmp+12+last_leaf_size]:
                for j in i[2]:
                    temp.append(j[1])
                new_mbr = upperMBR(temp)
                temp_level.append([i[1],new_mbr])
                temp=[]
            temp = []
            this_level.append([1,n_id,temp_level])
            temp_level = []
            n_id+=1
            
            for i in lowerLevel[tmp+12+last_leaf_size:len(lowerLevel)]:
                for j in i[2]:
                    temp.append(j[1])
                new_mbr = upperMBR(temp)
                temp_level.append([i[1],new_mbr])
                temp=[]
            temp = []
            this_level.append([1,n_id,temp_level])
            temp_level = []
            n_id+=1
        
        
        print(len(this_level), 'nodes at level', lvl)
        lvl += 1
        
        if(nodes<=20):
            root = topLevel(this_level,n_id,lvl)
            this_level.append(root)
            
        else:
            tmp = upperLevel(this_level,n_id,lvl)
            for i in tmp:
                this_level.append(i)
        
        
    return this_level
    
    
        
    

Coords = open(sys.argv[1], "r")
Offsets = open(sys.argv[2], "r")


z=[]
records = []

for line in Offsets:
    
    id, startOffset, endOffset = line.split(',')
    id = int(id)
    
    x_list = []
    y_list = []
    
    for i in range(int(startOffset),int(endOffset)+1):
        
        coor = Coords.readline()
        x, y = coor.split(',')
        
        x_list.append(float(x))
        y_list.append(float(y))
    
    x_low=min(x_list)
    x_high=max(x_list)
    y_low=min(y_list)
    y_high=max(y_list)
            
    records.append([id,[x_low, x_high, y_low, y_high]])
    
    z.append(int(interleave_latlng((y_high+y_low)/2,(x_high+x_low)/2)))


 
Coords.close()
Offsets.close()

lvl0 = []

zmax = max(z) + 1

for i in range(0,len(z)):
    
    z_index = z.index(min(z))
    lvl0.append(records[z_index])
    z[z_index] = zmax

num_leafs = ceil(len(lvl0)/20)

#all the leafs are in Rtree0 
Rtree0 = []
#this will help determine if splitting needs to be done
extras = 20*num_leafs - len(lvl0)
#ousiastika posa leipoun wste na einai pol/sia toy 20


#we have to fill the last leaf with objects from the second from last leaf
if(extras > 12):
    #20 objects per leaf except for the last 2 leafs
    for i in range(0,num_leafs-2):
        leaf = []
        
        for j in range(0,20):
            leaf.append(lvl0[j+20*i])
            
        Rtree0.append([0,i,leaf])
        
    leaf = []
    
    #fill the second from last leaf
    for j in range(0,20-(extras-12)):
        leaf.append(lvl0[j+20*(num_leafs-2)])
        
    Rtree0.append([0,len(Rtree0),leaf])
    
    leaf = []
    
    #fill the last leaf with the remaining 8 items
    for j in range(len(lvl0)-9,len(lvl0)-1):
        leaf.append(lvl0[j])
        
    Rtree0.append([0,len(Rtree0),leaf])
    
#else no splitting needs to be done    
else:
    #last leaf will have >=8 items
    for i in range(0,num_leafs-1):
        leaf = []
        
        for j in range(0,20):
            leaf.append(lvl0[j+20*i])
            
        Rtree0.append([0,i,leaf])
        
    #we also have to add the last items even when they belong in [8,20]
    left = len(lvl0)-20*(num_leafs-1)
    leaf = []
    for j in range(0,left):
        leaf.append(lvl0[j+20*(num_leafs-1)])
    Rtree0.append([0,i+1,leaf])

node_id = num_leafs

print(num_leafs, ' nodes at level 0')

#call upper to create levels above the leafs
Rtree1 = upperLevel(Rtree0, node_id, 1)




with open('Rtree.txt', 'w') as out:
    for i in Rtree0:
        out.write(str(i)+'\n')
    for i in Rtree1:
        out.write(str(i)+'\n')
