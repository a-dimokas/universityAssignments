#Aggelos Dimokas 2683
#Simeon Taglaridis 2828

import tensorflow as tf
from sklearn import metrics
import numpy as np
from math import sqrt

fashion_mnist = tf.keras.datasets.fashion_mnist

(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']




#we used the test set in order to reduce time complexity               
train_images_1 = test_images / 255.0


from collections import Iterable

#scaling 28x28 images to 1x784
flat_train_images = []
for i in train_images_1:
    flat_list = [item for sublist in i for item in sublist]
    flat_train_images.append(flat_list)

    
flat_train_images = np.array(flat_train_images)


#we pick 600 samples from each category to train the models with
train_dict = {}
for i in range(0,10):
    train_dict[i] = []

for i in range(0,len(flat_train_images)):
    if (len(train_dict[train_labels[i]])<600):
        train_dict[train_labels[i]].append(i)
        
new_train_images = []
new_train_labels = []
for i in range(0,10):
    for j in train_dict[i]:
        new_train_images.append(flat_train_images[j])
        new_train_labels.append(train_labels[j])

train_images = np.array(new_train_images)
train_labels = np.array(new_train_labels)



#random data points as centroids R1 type
def datCentroidsR1(x):
    centroids = []
    for i in range(10):
        tmp = random.randrange(len(x))
        centroids.append(x[tmp])
    return centroids






from scipy.spatial import distance

#returns the most distand point from the centroids
def distances(c,x,type):
    distances = []
    if(type=='EUC'):
        for i in x:
            suma = 0
            for j in c:
                suma += distance.euclidean(i,j)
            distances.append(suma)
        return distances.index(max(distances))
    elif(type=='MAN'):
        for i in x:
            suma = 0
            for j in c:
                suma += distance.cityblock(i,j)
            distances.append(suma)
        return distances.index(max(distances))
    elif(type=='COS'):
        for i in x:
            suma = 0
            for j in c:
                suma += distance.cosine(i,j)
            distances.append(suma)
        return distances.index(max(distances))


def closeCentroid(c,x,type):
    centroid = []
    if(type=='EUC'):
        for i in x:
            distances=[]
            for j in c:
                distances.append(distance.euclidean(i, j))
            centroid.append(np.argmin(distances))
    elif(type=='MAN'):
        for i in x:
            distances=[]
            for j in c:
                distances.append(distance.cityblock(i,j))
            centroid.append(np.argmin(distances))
    elif(type=='COS'):
        for i in x:
            distances=[]
            for j in c:
                distances.append(distance.cosine(i,j))
            centroid.append(np.argmin(distances))
    elif(type=='KL'):
        for i in x:
            similarities = []
            for j in c:
                similarities.append(kl_divergence(i,j))
            centroid.append(np.argmax(similarities))
    return centroid

from statistics import mean

def updateCentroids(c,x):
    clustered_data = []
    for i in range(10):
        clustered_data.append([])
    cc = 0
    for i in c:
        clustered_data[i].append(x[cc])
        cc += 1
    
    centroids = []

    tmp_list = []
    for i in range(x.shape[1]):
        tmp_list.append([])

    for i in clustered_data:
        tmp = tmp_list
        
        for j in i:
            cc = 0
            for p in j:
                tmp[cc].append(p)
                cc += 1
            
        means = []
        for o in range(x.shape[1]):
            means.append(mean(tmp[o]))
        centroids.append(means)
    return centroids

K = [0,1,2,3,4,5,6,7,8,9]

def kMeans(type):


    centroidsLast = datCentroidsR1(train_images)
    for i in range(7): #number of iterations

        label_centroids = closeCentroid(centroidsLast,train_images,type)
        centroids = updateCentroids(label_centroids,train_images)


        #find most distant point and make it the new centroid for empty culster
        for k in K:
            if(k not in label_centroids):
                tmp = label_centroids[:k] + label_centroids[(k + 1):]
                
                point = distances(tmp,train_images,type)
                centroids[k] = train_images[point]
            


        #if centroids did not change then kmeans finished
        #or if no data point has been reassigned to diff cluster
        if(np.array_equal(np.array(centroidsLast), np.array(centroids))):
            return centroidsLast, label_centroids

        centroidsLast = centroids
    return label_centroids


flat_train_images = flat_train_images*255
#brightness histogram bin representation
def r2(m):

    bins = []
    for i in range(len(flat_train_images)):
        tmp = []
        for j in range(256//m):
            tmp.append(0)
        bins.append(tmp)

    index = 0
    for i in flat_train_images:
        for x in i:
            bins[index][int(x//m)] += 1
        index += 1

    return bins


r2_dict = {}
for m in [16,32,64,128]:
    r2_dict[m] = r2(m)


import random

# random centroids R2 type
def centroidsR2(m):
    centroids = []
    for i in range(10):
        tmp = random.randrange(len(r2_dict[m]))
        centroids.append(r2_dict[m][tmp])
    return centroids

import math
def kl_divergence(p, q):
    result = 0.0
    for i in range(len(p)):
        if(p[i]>0 and q[i]>0):
            result += p[i] * math.log((p[i]/q[i]),2)

    return result

def updateR2(c,x):
    clustered_data = []
    for i in range(10):
        clustered_data.append([])
    cc = 0
    for i in c:
        clustered_data[i].append(x[cc])
        cc += 1
    
    centroids = []

    for i in range(10):
        centroids.append(np.mean(clustered_data[i],axis=0))

    return centroids

#returns index of least similar point from other clusters
def leastSim(c,x,k):

    tmp = c
    similar = []
    for i in x:
        suma = 0
        counter = -1
        for j in tmp:
            counter += 1
            if(isinstance(j, list)):
                suma += kl_divergence(i,j)
            elif(counter == k):
                continue
            else:
                continue
                
        similar.append(suma)
    tmp = np.array(similar)
    idx = np.argpartition(tmp, 10)
    return (idx[:10])
        
        

def kmedoid():
    results = []
    for i in r2_dict:
        centroidsLast = centroidsR2(i)
        for j in range(7): #number of iterations
            label_centroids = closeCentroid(centroidsLast,r2_dict[i],'KL')
            centroids = updateR2(label_centroids,r2_dict[i])
            for o in centroids:
                o = o.tolist()
                if(not isinstance(o, list)):
                    o = []
            
            #find the most non-similar point and make it the new centroid for empty culster
            don = []
            for k in [9,8,7,6,5,4,3,2,1,0]:
                if(k not in label_centroids):
                    
                    tmp = leastSim(centroids,r2_dict[i],k)
                    least = tmp[0]
                    counter = 0                  
                    while((least in don) or (counter==len(tmp))):
                        least = tmp[counter]
                        counter += 1
                        
                    don.append(least)

                    centroids[k] = r2_dict[i][least]
                    label_centroids[least] = k



            #if centroids did not change then kmedoid finished
            #or if no data point has been reassigned to diff cluster
            if(np.array_equal(np.array(centroidsLast), np.array(centroids))):
                break

            centroidsLast = centroids
        results.append(label_centroids)
    return results


#assigned a label according to the most common labels of the true_labels
def assignLabels(c_labels,true_labels):
    some_list = []
    for i in range(10):
        some_list.append([])
    for i in range(len(c_labels)):
        some_list[c_labels[i]].append(true_labels[i])
    #finds the most frequent label
    returnList = []
    for i in range(10):
            returnList.append(max(set(some_list[i]), key = some_list[i].count))
    return returnList


def purity(pred_labels,true_labels):
    assigned = assignLabels(pred_labels,true_labels)

    sums = []
    counters = []
    for i in assigned:
        sums.append(0)
        counters.append(0)


    for i in range(len(pred_labels)):
        counters[true_labels[i]] += 1
        if(true_labels[i]==assigned[pred_labels[i]]):
            sums[true_labels[i]] += 1

    purities = []
    for i in range(len(assigned)):
        purities.append(100*(sums[i]/counters[i]))
        
    return purities



def f1(actual, predicted, label):
    tp, fp, fn = 0,0,0
    for i in range(len(actual)):
        if(actual[i]==label & predicted[i]==label): tp+= 1
        elif(actual[i]!=label) & (predicted[i]==label): fp+= 1
        elif(predicted[i]!=label) & (actual[i]==label): fn += 1
    if(tp+fp == 0):
        precision = 0
        if(tp+fn == 0):
            return 0
        recall = tp/(tp+fn)
    elif(tp+fn==0):
        recall = 0
        precision = tp/(tp+fp)
    else:
        precision = tp/(tp+fp)
        recall = tp/(tp+fn)
    if((precision + recall)<=0):
        return 0
    f1 = (2 * precision * recall) / (precision + recall)
    return f1

def F_measure(pred_labels, true_labels):
    sums = 0
    for i in range(10):
        sums += f1(true_labels[600*i:600*(i+1)],pred_labels[600*i:600*(i+1)],i)
    return sums






def means2(data,type):
    centroids = []
    for i in range(2):
        tmp = random.randrange(len(data))
        centroids.append(data[tmp])
    for i in range(50):
        clusters = [[],[]]
        for x in data:
            distances = []
            for y in centroids:
                if(type=='EUC'):
                    distances.append(distance.euclidean(x, y))
                elif(type=='MAN'):
                    distances.append(distance.cityblock(x, y))
                elif(type=='COS'):
                    distances.append(distance.cosine(x, y))
                elif(type=='KL'):
                    distances.append(-kl_divergence(x, y))
            clusters[np.argmin(distances)].append(x)

        #recalculate centroids for each cluster  
        counter = 0  
        for i in clusters:
            centroids[counter] = np.mean(i)
            counter += 1

        
    return clusters




def divisMeth(type):
    k = []
    k.append(train_images)

    while(len(k)<10):
        tmp = []
        for i in k:
            tmp.append(np.var(i))
        maxVarInd = tmp.index(max(tmp))

        split = k[maxVarInd]

        tmp = means2(split,type)
        k[maxVarInd] = tmp[0]
        k.append(tmp[1])

    return k 




def purity2(centroidList):
    labels = []
    purities = []
    tmpLab = 0
    for i in centroidList:
        if(len(i)>0):
            tmp = []
            for j in i:
                tmp.append(train_labels[np.where(train_images==j)[0][0]])
            labels.append(max(tmp, key = tmp.count))
            counter = 0
            for j in i:
                if(train_labels[np.where(train_images==j)[0][0]] == labels[tmpLab]):
                    counter += 1
            purities.append(100*counter/len(i))
            tmpLab += 1
        else:
            purities.append(0.0)
    return purities


label_centroidsEUC  = kMeans('EUC')
label_centroidsMAN  = kMeans('MAN')
label_centroidsCOS  = kMeans('COS')
label_centroidsKL  = kmedoid()

resultEUC = purity(label_centroidsEUC,train_labels)
fEUC = F_measure(label_centroidsEUC,train_labels)

resultMAN = purity(label_centroidsMAN,train_labels)
fMAN = F_measure(label_centroidsMAN,train_labels)

resultCOS = purity(label_centroidsCOS,train_labels)
fCOS = F_measure(label_centroidsCOS,train_labels)

resultKL = []
fKL = []
cc = 0
for i in label_centroidsKL:
    resultKL.append(purity(i,test_labels))
    fKL.append(F_measure(i,test_labels))
    print('Kmedoid with KL divergence and R2 histogram ',list(r2_dict.keys())[cc],'-bin representation \n purity for each category:', resultKL[cc], '\nmean purity of medoid with KL:', mean(resultKL[cc]), '%')
    print('Kmedoid F-measure with KL divergence metric:', fKL[cc])
    cc += 1

print('Kmeans Euclidean purity for each category:', resultEUC, '\nmean purity of kmeans with Euclidean distance metric:', mean(resultEUC), '%')
print('Kmeans F-measure with Euclidean distance metric:', fEUC)

print('Kmeans Manhattan purity for each category:', resultMAN, '\nmean purity of kmeans with Manhattan distance metric:', mean(resultMAN), '%')
print('Kmeans F-measure with Manhattan distance metric:', fMAN)

print('Kmeans Cosine purity for each category:', resultCOS, '\nmean purity of kmeans with Cosine distance metric:', mean(resultCOS), '%')
print('Kmeans F-measure with Cosine distance metric:', fCOS)





tmp = divisMeth('EUC')
resultEUC = purity2(tmp)
print('Hier. Cluster with division method R1 Euclidean distance purity:', resultEUC, '\n and mean purity:', mean(resultEUC))

tmp = divisMeth('MAN')
resultMAN = purity2(tmp)
print('Hier. Cluster with division method R1 Manhattan distance purity:', resultMAN, '\n and mean purity:', mean(resultMAN))

tmp = divisMeth('COS')
resultCOS = purity2(tmp)
print('Hier. Cluster with division method R1 Cosine distance purity:', resultCOS, '\n and mean purity:', mean(resultCOS))

tmp = divisMeth('KL')
resultCOS = purity2(tmp)
print('Hier. Cluster with division method R2 Histrogram with kl-divergence, purity:', resultCOS, '\n and mean purity:', mean(resultCOS))




