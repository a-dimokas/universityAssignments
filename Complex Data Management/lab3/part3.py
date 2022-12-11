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
        for i in range(3,len(node)-1):
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

def djikstraStep(visited,queue,spd):
    while(True):
        tmp = heapq.heappop(queue)
        v = tmp[1]
        spd[v] = tmp[0]
        visited.append(v)

        for i in range(len(graph[v][3])//2):
            u = graph[v][3][2*i]
            dist = graph[v][3][2*i + 1]
            
            if(u not in visited):
                if(spd[u] > spd[v]+dist):
                    spd[u] = spd[v]+dist                   
                    heapq.heappush(queue,(spd[u],u))
        yield v,visited,queue,spd
        


def NRA(nodes):
    tmp = []
    for i in range(len(graph)):
        tmp.append(float('inf')) 
    
    spds = []
    queues = []
    visits = []
    tmp_node = []



    for node in nodes:
        spd = tmp
        spd[node] = 0
        visit = []
        queue = []
        heapq.heappush(queue,(spd[node],node))


        tmp_node.append(0)
        visits.append(visit)
        spds.append(spd)
        queues.append(queue)
    dists = []

    
    for i in range(len(nodes)):
        tmp_node[i], visits[i], queues[i], spds[i] = next(djikstraStep(visits[i], queues[i], spds[i]))
        tmp_node[i], visits[i], queues[i], spds[i] = next(djikstraStep(visits[i], queues[i], spds[i]))

    #contains nodes that have been fully explored
    done = []
    
    tmp = []
    for i in range(len(nodes)):
        tmp.append(float('inf'))

    while(True):

        

        for i in range(len(nodes)):
            if i in done:
                tmp[i] = float('inf')
            else:
                tmp[i] = spds[i][tmp_node[i]]
        
        #sorting with indexes the tmp list with the paths.
        mins = sorted(range(len(tmp)), key=lambda k: tmp[k])
        
        if(queues[mins[0]]):
            
            tmp_node[mins[0]], visits[mins[0]], queues[mins[0]], spds[mins[0]] =\
                 next(djikstraStep(visits[mins[0]], queues[mins[0]], spds[mins[0]]))
            check = 0
            for j in range(len(nodes)):
                if(tmp_node[mins[0]] in visits[j]):
                    check += 1
            if(check == len(nodes)):
                for j in range(len(nodes)):
                    dists.append(spds[j][tmp_node[mins[0]]])
                return tmp_node[mins[0]], dists

        else:
            done.append(mins[0])

nodeList = []
for i in sys.argv[1:]:
    nodeList.append(int(i))

node, dists = NRA(nodeList)

print('for nodes: ', nodeList)
for i in dists:
    print(i)
print('node:', node)
