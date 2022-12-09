#Aggelos Dimokas

import tensorflow as tf
from sklearn import metrics
import numpy as np

fashion_mnist = tf.keras.datasets.fashion_mnist

(train_images, train_labels), (test_images, test_labels) = fashion_mnist.load_data()

class_names = ['T-shirt/top', 'Trouser', 'Pullover', 'Dress', 'Coat',
               'Sandal', 'Shirt', 'Sneaker', 'Bag', 'Ankle boot']



train_images = train_images / 255.0

test_images = test_images / 255.0


from collections import Iterable

#scaling 28x28 images to 1x784
flat_train_images = []
for i in train_images:
    flat_list = [item for sublist in i for item in sublist]
    flat_train_images.append(flat_list)
flat_test_images = []
for i in test_images:
    flat_list = [item for sublist in i for item in sublist]
    flat_test_images.append(flat_list)
    
flat_train_images = np.array(flat_train_images)
flat_test_images = np.array(flat_test_images)


        
#we pick 2000 samples from each category to train the models with
train_dict = {}
for i in range(0,10):
    train_dict[i] = []

for i in range(0,len(train_labels)):
    if (len(train_dict[train_labels[i]])<2000):
        train_dict[train_labels[i]].append(i)
        
new_train_images = []
new_train_labels = []
for i in range(0,10):
    for j in train_dict[i]:
        new_train_images.append(flat_train_images[j])
        new_train_labels.append(train_labels[j])

new_train_images = np.array(new_train_images)
new_train_labels = np.array(new_train_labels)



def printResults(y_true,y_pred):
    print('\t Average Accuracy score:',metrics.accuracy_score(y_true,y_pred))
    cc = 0
    for i in class_names:
        print('for class:',i)
        print('\t F1 score:',metrics.f1_score(y_true,y_pred, average = None)[cc])
        cc+=1


#----------METHOD 1-------------------
from sklearn.neighbors import KNeighborsClassifier
x = [1,5,10]

print('KNN with euclidean metric distance EVALUATION: ')
for k in x:
    print('FOR K =', k)
    modelKNN = KNeighborsClassifier(n_neighbors=k, weights='distance', metric='euclidean')
    modelKNN.fit(new_train_images, new_train_labels)
    images_pred = modelKNN.predict(flat_test_images)
    printResults(images_pred,test_labels)

print('KNN with cosine metric distance EVALUATION ')
for k in x:
    print('FOR K =', k)
    modelKNN = KNeighborsClassifier(n_neighbors=k, weights='distance', metric='cosine')
    modelKNN.fit(new_train_images, new_train_labels)
    images_pred = modelKNN.predict(flat_test_images)
    printResults(images_pred,test_labels)
        
    


#----------METHOD 2-------------------
from sklearn.neural_network import MLPClassifier

modelNN = MLPClassifier(hidden_layer_sizes=(500,), activation='logistic', solver='sgd')
modelNN.n_outputs_ = 10
modelNN.out_activation_ = 'softmax'
modelNN.fit(new_train_images, new_train_labels)
images_pred = modelNN.predict(flat_test_images)
    
print('NEURAL NETWORK with 1 layer of 500 neurons. EVALUATION:')
printResults(images_pred,test_labels)

modelNN = MLPClassifier(hidden_layer_sizes=(500,200), activation='logistic', solver='sgd')
modelNN.n_outputs_ = 10
modelNN.out_activation_ = 'softmax'
modelNN.fit(new_train_images, new_train_labels)
images_pred = modelNN.predict(flat_test_images)
    
print('NEURAL NETWORK with 2 layer of 500 and 200 neurons respectively. EVALUATION:')
printResults(images_pred,test_labels)


#----------METHOD 3-------------------
from sklearn import svm

modelSVM = svm.SVC(kernel='linear', decision_function_shape='ovr', gamma='auto')
modelSVM.fit(new_train_images, new_train_labels)
images_pred = modelSVM.predict(flat_test_images)
print('SVM with linear kernel. EVALUATION:')
printResults(images_pred,test_labels)

modelSVM = svm.SVC(kernel='rbf', decision_function_shape='ovr', gamma='auto')
modelSVM.fit(new_train_images, new_train_labels)
images_pred = modelSVM.predict(flat_test_images)
print('SVM with Gaussian kernel function (C=1). EVALUATION:')
printResults(images_pred,test_labels)

modelSVM = svm.SVC(C=0.7, kernel='rbf', decision_function_shape='ovr', gamma='auto')
modelSVM.fit(new_train_images, new_train_labels)
images_pred = modelSVM.predict(flat_test_images)
print('SVM with Gaussian kernel function (C=0.7). EVALUATION:')
printResults(images_pred,test_labels)

modelSVM = svm.SVC(kernel=metrics.pairwise.cosine_similarity, decision_function_shape='ovr', gamma='auto')
modelSVM.fit(new_train_images, new_train_labels)
images_pred = modelSVM.predict(flat_test_images)
print('SVM with sigmoid function, cosine kernel. EVALUATION:')
printResults(images_pred,test_labels)


#----------METHOD 4-------------------
from math import sqrt,pi,exp,log

def calculate_probability(x, mean, stdev):
    exponent = exp(-((x-mean)**2 / (2 * stdev**2 )))
    return (1 / (sqrt(2 * pi) * stdev)) * exponent


def trainNaive(images,labels,tests):
    
    means = np.empty((images.shape[1],10), float)
    
    for x in range(0,10):
        for i in range(0,images.shape[1]):
            
            sumi = 0
            for j in range(x*2000,(x*2000)+2000):
                sumi+=images[j][i]
            means[i][x] = sumi/2000.0
    
    
    varian = np.zeros((images.shape[1],10), float)
    variance = np.zeros((images.shape[1],10), float)
    
    for x in range(0,10):
        for i in range(0,images.shape[1]):
            
            for j in range(x*2000,(x*2000)+2000):
                varian[i][x] += (images[j][i]-means[i][x])**2
                

            variance[i][x]= varian[i][x]/2000.0
    

    probabilities=[]

    for x in range(0,tests.shape[0]):
        probs = []
        for j in range(0,10):
        
            yolo= 1.0
            for i in range(0,tests.shape[1]):
                if(variance[i][j]>0):
                    temp = calculate_probability(tests[x][i],means[i][j],sqrt(variance[i][j]))
                    if(temp>0):
                        yolo *= temp
                    
                
            probs.append(yolo)
            
        
        
        probabilities.append(probs.index(max(probs)))
    
    
    
    return probabilities

im_pred  = trainNaive(new_train_images, new_train_labels,flat_test_images)


print('Naive Bayes classification model. EVALUATION:')
printResults(im_pred,test_labels)





