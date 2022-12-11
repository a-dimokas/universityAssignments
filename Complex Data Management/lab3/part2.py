# AUTHOR: Angelos Dimokas 2683
import heapq
import sys
import math

def readGraph():
    nodes = []
    graphFile = open("out.txt", "r")
    for line in graphFile:
        node = line.split(' ')
        node[0] = int(node[0])
        node[1] = float(node[1])
        node[2] = float(node[2])

        flag = True
        neigh = []
        for i in range(3,len(node)-1):#node[3:len(node)-1]:
            if flag:
                neigh.append(int(node[i]))
                flag = False
            else:
                neigh.append(float(node[i]))
                flag = True
        tmp = [node[0],node[1],node[2],neigh]
        nodes.append(tmp)
    return nodes
#read and store the graph
graph = readGraph()

#Djikstra    

#source node s, target node t
def djikstra(s,t):
    Q = [] #priority queue
    nodesVisited = [] #contains all the nodes visited from the function
    path = [] #contains nodes that are included in the path
    SPD = [] #shortest path distance
    
    for i in range(len(graph)):
        SPD.append(float('inf')) 
        path.append([])
    
    SPD[s]=0.0
    path[s] = [s]
    heapq.heappush(Q,(SPD[s],s))

    while(Q):

        tmp = heapq.heappop(Q)
        v = tmp[1]
        SPD[v] = tmp[0]
        nodesVisited.append(v)
        

        if (v==t):
            return len(nodesVisited), SPD[t], len(path[t]), path[t]
            
        for i in range(len(graph[v][3])//2):
            u = graph[v][3][2*i]
            dist = graph[v][3][2*i + 1]
            
            if(u not in nodesVisited):
                if(SPD[u] > SPD[v]+dist):
                    SPD[u] = SPD[v]+dist
                    path[u] = path[v] + [u]                    
                    heapq.heappush(Q,(SPD[u],u))
    
    return 0


def alphaStar(s,t):
    Q = [] #priority queue
    nodesVisited = []#contains all the nodes visited from the function
    path = [] #contains nodes that are included in the path
    SPD = [] #shortest path distance
    dist = [] #contains the euclidean distance between all the nodes and the target node
    xt = graph[t][1]
    yt = graph[t][2]
    
    for i in range(len(graph)):
        SPD.append(float('inf')) 
        path.append([])
        x = graph[i][1]
        y = graph[i][2]
        dist.append(math.sqrt(pow((x-xt),2)+pow((y-yt),2)))
    
    SPD[s]=0.0
    path[s] = [s]
    heapq.heappush(Q,(SPD[s]+dist[s],s))

    while(Q):

        tmp = heapq.heappop(Q)
        v = tmp[1]
        SPD[v] = tmp[0]-dist[v]
        nodesVisited.append(v)
        

        if (v==t):
            return len(nodesVisited), SPD[t], len(path[t]), path[t]
            
        for i in range(len(graph[v][3])//2):
            u = graph[v][3][2*i]
            d = graph[v][3][2*i + 1]
            
            if(u not in nodesVisited):
                if(SPD[u]+dist[u] > SPD[v]+dist[v]+d): 
                    SPD[u] = SPD[v]+d
                    path[u] = path[v] + [u]                    
                    heapq.heappush(Q,(SPD[u]+dist[u],u))
    
    return 0

s = int(sys.argv[1])
t = int(sys.argv[2])
dj = djikstra(s,t)
aStar = alphaStar(s,t)

out = sys.stdout

if(dj==0):
    out.write('Djikstra did not find a path between the nodes you entered')
    if(aStar==0):
        out.write('Alpha Star did not find a path between the nodes you entered')
        
else:
    output = ['nodes visited = ','distance from source to target node = ','shortest path length = ','shortest path = ']
    
    out.write('Djikstra:')
    out.write('source node = '+str(s)+'\t and target node = '+str(t)+'\n')
    cc = 0
    for i in dj:
        out.write(output[cc]+str(i)+'\n')
        cc += 1
    
    out.write('\nAstar:')
    out.write('source node = '+str(s)+'\t and target node = '+str(t)+'\n')
    cc = 0
    for i in aStar:
        out.write(output[cc]+str(i)+'\n')
        cc += 1



