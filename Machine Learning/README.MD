# MACHINE LEARNING

## In this course we were taught machine learning algorithm along with our own implementation of them.

---

### Part 1

#### Classification Methods

In this part of the course we were assigned to **Classify images of clothing** from images of the [fashion MNIST dataset](https://www.tensorflow.org/tutorials/keras/classification).
The dataset consists of 60.000 images of 10 categories of clothing and another 10.000 for testing purposes.
To run the code found in part1.py the installation of [Anaconda](www.anaconda.com) and Python3 is greatly advised. Also tensorflow and scikit are essential to install with the commands "conda isntall tensorflow" and "conda install scikit-learn scipy matplotlib" in Anaconda.
Alternatively use "pip install scikit-learn" in Visual Studio Code to install the necessary 


#### 1.Purpose
<p> The purpose of this assignment was to study and experiment with the following classification algorithms and methods on a real dataset:

- **Nearest Neighbor k-NN** with Eucleidian distance and with cosine distance.

- **Neural Networks**

- **Support Vector Machines (SVMs)** with linear kernel, Gaussian kernel and cosine kernel.

- **Naïve Bayes classifier** with normal distribution

</p> 

#### 2.Testing 
<p>
For Testing purposes we used the following evaluation metrics:
  
*TP: true positives, TN: true negative, FN: false negative, FP: false positives, P: positives, N: negatives.*

- **Accuracy** is the percentage of success of the decisions the classifier makes.

![image](https://user-images.githubusercontent.com/91612373/206761897-9b785d5a-7d0c-463d-ac36-8fe8c95cdc74.png)

- **F1 score**
  
![image](https://user-images.githubusercontent.com/91612373/206761920-67742556-094c-4d17-a008-4bb37695624d.png)


</p> 

#### 3.Development 
<p>
  In this section some of the most notable function will be explained briefly.
  
  - **Preproccessing Data**: For the preprocess of the data we formatted the data so that for each image
(28x28) has its data in vector format (1x784).
Converted values 0-255 for each attribute to values from 0 to 1.
Additionally, to reduce the learning time of the models, we kept from the datasets
learning 2000 images (out of a total of 6000) for each clothing category. Which we used for
learning the classification models on the training data.

  - **printResults**: This function is responsible for printing the results of each prediction
method using the evaluation metrics.

  - **KNN**: we used the in-built method of the sklearn.neighbors.KNeighborsClassifier package.

  - **NeuralNetworks**: we used the in-built method of the sklearn.neural_network.MLPClassifier package. For
its output we parameterized the variable n_outputs and for the activation function the
out_activation.

  - **SVM**: we used the in-built method of the sklearn.svm.SVC package.

  - **Naïve Bayes**: For its implementation, we used the basic mathematical functions of root, exponential and
logarithm and the value of π. The calculate_propability function returns the probability of
feature of an image given a category.
  
</p> 

#### 4.Results 
<p>
  
  - **k-NN**: for k = 1,5,10
  
The classifier ended with the following metrics with euclidean distances:
  
  ![image](https://user-images.githubusercontent.com/91612373/206764553-5ea72f83-915d-49a5-a6ce-b0cc37704419.png)

  
And with the following for cosine distances"
  
  ![image](https://user-images.githubusercontent.com/91612373/206764586-6ee8e64a-cb7d-4141-8110-7a26654eb8fc.png)

Comparing the results we can see the **k-NN** method performs better for k = 5 and with cosine distances.
  
  - **Neural Network** method with sigmoid function, Stochastic gradient descent training, 10 output neurons and softmax activation function. 
  
1 level of 500 Neurons
  
  ![image](https://user-images.githubusercontent.com/91612373/206765090-55cd91d5-06c4-4c05-848c-f8c778690b13.png)
  
2 levels of 500 and 200 Neurons respectively
  
  ![image](https://user-images.githubusercontent.com/91612373/206765128-4c190ab6-e261-4e85-b875-506c9b3a753a.png)
  
In this method we see a decrease in accuracy and F1 score for the 2 level Neural Network.
  
  - **SVMs** method:
 
With linear kernel:
  
  ![image](https://user-images.githubusercontent.com/91612373/206765645-6ff3686a-3656-4040-b2f1-3e13ba98a35c.png)

With Gaussian kernel of value C=1:
  
  ![image](https://user-images.githubusercontent.com/91612373/206765674-6c55c756-ae10-457f-bb08-1356f7751577.png)

With Gaussian kernel of value C=0.7:
  
  ![image](https://user-images.githubusercontent.com/91612373/206765660-3c8669e2-51c9-4f3b-be88-d4989079003e.png)

With cosine kernel:
  
  ![image](https://user-images.githubusercontent.com/91612373/206765690-ce2cd2e0-fea7-4059-8519-c37c57021f86.png)

  
In general SVMs have better evaluation metrics from the methods above, especially when used with cosine kernel function we osserve the best metrics so far.
  
  - **Naïve Bayes** method with normal distribution for each category:
  
  ![image](https://user-images.githubusercontent.com/91612373/206765987-49579f9a-5847-4bb3-8ff5-02cfcd7b4385.png)

Naïve Bayes fails to classify at a good rate
and it seems that it only succeeds in 2-3 categories
of clothing, while in other categories it has very large reductions in
its performance compared to previous methods
 
</p> 


---

### Part 2

#### Data Grouping Methods

For this part we used the same dataset as in part 1.

#### 1. Purpose

<p>
 The purpose of this exercise is
to achieve the (unsupervised) analysis of their structure through
of the process of grouping, i.e. of the optimal separation of the set
data into K=10 groups
</p> 


#### 2.Testing 
<p>
For Testing purposes we used the following evaluation metrics:

- **Purity** The category of each group is determined, after the end of grouping,
by the majority real category among the group members.
Then the accuracy (purity) is calculated by measuring the percentage of correctly 
classified data

- **F-measure** For each cluster, after specifying the majority category as the class
cluster (as in the previous measure), find the TP (true positive), FP
(false positive) and FN (false negative) and then the F1-score (see
exercise1). In the end, the evaluation of the clustering method will result from the
sum of F-measures for each cluster
  
![image](https://user-images.githubusercontent.com/91612373/206768818-b1540c39-2d00-4123-acfc-2f4966f16581.png)

</p> 

#### 3.Development 
<p>
  For the processing of the data we used 2 alternative type of data for the repsesantation of each image:
  
  - **R1**: 28x28=784 vector where each component will represent the
brightness value of each pixel of the image. Normalized the values by dividing by 255 which is the maximum brightness value and
corresponds to deep black for easier operations.
  
  - **R2**: luminance histogram using M bins (M = 16 or 32 or 64 or 128.
  
  Below we present the methods we developed and used in our metrics for the grouping of the data.

  - **classic K-means clustering**: using Euclidean distance (L2), Manhattan distance (L1) and cosine distance.
  
  - **K-Medoid clustering**: only for R2 type data using symmetric Kullback- Libler (KL) distance, which is a function of distance between allocations.
  
  - **Hierarchical clustering**: using the divisive or linkage form of construction of the data tree for all of the above forms of distance.

</p> 

#### 4.Results 
<p>
  
</p> 
