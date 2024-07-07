## Assignment for Personal Development - Net-Company

<p align="center"> 
<img src="https://github.com/BardisRenos/AppNetCompany/blob/main/src/main/java/com/example/app/images/kubernetes.png" width="750" height="450" style=centerme>
</p>

### Info

Create an application in Spring Boot with Kubernetes and Docker.

Requirements:
- Develop an application with Kubernetes
- Emphasizing to Kubernetes

Prerequisites 
- Java v17
- Spring Boot v3.2.7
- Maven Project 
- Maven Build Tool
- Lombok (Additional Library)
- Minikube 1.33.0
- Ubuntu 22.04

### What is Kubernetes 
Kubernetes is a portable, extensible, open-source platform for managing containerized workloads and services, that facilitates both declarative configuration and automation. It has a large, rapidly growing ecosystem. Kubernetes services, support, and tools are widely available.

### Kubernetes Components 
When you deploy Kubernetes, you get a cluster.

A Kubernetes cluster consists of a set of worker machines, called nodes, that run containerized applications. Every cluster has at least one worker node.

The worker node(s) host the Pods that are the components of the application workload. The control plane manages the worker nodes and the Pods in the cluster. In production environments, the control plane usually runs across multiple computers and a cluster usually runs multiple nodes, providing fault-tolerance and high availability.


<p align="center"> 
<img src="https://github.com/BardisRenos/AppNetCompany/blob/main/src/main/java/com/example/app/images/kubernetesCluster.png" width="750" height="450" style=centerme>
</p>


### Setting the Application

- Setting the Application properties

We are changing the server port from 8080 (Default) to 8088.

```
server:
  port: '8088'
```
The whole application.yml file consists of the configuration of the application regarding the memory database H2 and the hibernate configuration. 

### Docker

-  Creating the Dockerfile for the application.

```
FROM openjdk:17-alpine
LABEL maintainer="Renard (Renos) Bardis"
EXPOSE 8089
ADD target/app-net-company.jar app-net-company.jar
CMD ["java", "-jar", "/app-net-company.jar"]
```

### Kubernetes (file structure)
This is a Kubernetes file (manifests) the format is .yml formatting. The file contains both the Deployment and Service type. 

```
apiVersion: apps/v1
kind: Deployment
metadata:
  name: account-service-app
  labels:
    app: account-service-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: account-service-app
  template:
    metadata:
      labels:
        app: account-service-app
    spec:
      containers:
        - name: account-service-app
          image: renosbardis/app
          imagePullPolicy: Always
          ports:
            - containerPort: 8088
---
apiVersion: v1
kind: Service
metadata:
  name:  account-service-svc
spec:
  selector:
    app:  account-service-app
  ports:
    - name: account-app
      protocol: "TCP"
      port: 80
      targetPort: 8088
      nodePort: 30011
  type: LoadBalancer
```

### Kubernetes manifest fields
All Kubernetes manifests have a few required fields:

- **apiVersion** — This states the Kubernetes API that the object type you are creating belongs to. Top-level objects such as Pods currently belong to the v1 API, whereas other built-ins are scoped to more specific APIs: Deployments live in apps/v1, for example. Custom Resource Definitions (CRDs) define their API versions that let you reference the objects they provide.

-  **kind** — This is the type of object that you are defining, such as Pod or Deployment.

-  **metadata** — This field contains essential information about the object, including its name and namespace. This is covered in more detail below.

-  **spec** — Strictly speaking, this is not a required field, but it is used by most of the object types that are built into Kubernetes. This is where you will typically define the properties of your object, such as the container image used by a Pod or the number of replicas to run in a ReplicaSet.

### Manifest metadata
The metadata section is used to define your object’s identity and attach any relevant organizational data to it. It includes the following major fields:

-  **name** — This is the only required metadata field. It is the name your object will be assigned in Kubernetes.

-  **namespace** — When set, this references a Kubernetes namespace that the object will be created within. The namespace must already exist in your cluster. When this field’s omitted, the default namespace is used.

- **labels and annotations** — Labels and annotations let you add your own metadata to your objects. Labels are intended for information that identifies an object (such as the app or team it belongs to), while annotations store arbitrary values like the time the object was created or the system that it is being managed by.


### Installing minikube
To deploy minikube is local Kubernetes, focusing on making it easy to learn and develop for Kubernetes.

This link has all instructions to [Install](https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download#Service) minikube 

### How to deploy/start the minikube 
-  In Ubuntu when you want to start Kubernetes with Docker the first command you need to apply is ```eval $(minikube docker-env)```

-  To start minikube cluster, you need to type ```minikube start```

- To check if then status of the minikube status if it is running ```minikube status```

The result of the previous command
```minikube
type: Control Plane
host: Running
kubelet: Running
apiserver: Running
kubeconfig: Configured
docker-env: in-use
```

- To deploy the minikube **.yml** file
```kubectl apply -f accounts-deployment.yml```

The response of the command 
```
deployment.apps/account-service-app created
service/account-service-svc created
```

- To check the deployment

```kubectl get all ``` 

As a result, the command shows 

```
NAME                                      READY   STATUS    RESTARTS   AGE
pod/account-service-app-788f4d895-q6b4c   1/1     Running   0          5m45s

NAME                          TYPE           CLUSTER-IP     EXTERNAL-IP   PORT(S)        AGE
service/account-service-svc   LoadBalancer   10.102.92.72   <pending>     80:30011/TCP   5m45s
service/kubernetes            ClusterIP      10.96.0.1      <none>        443/TCP        7d1h

NAME                                  READY   UP-TO-DATE   AVAILABLE   AGE
deployment.apps/account-service-app   1/1     1            1           5m45s

NAME                                            DESIRED   CURRENT   READY   AGE
replicaset.apps/account-service-app-788f4d895   1         1         1       5m45s
```

- Show the IP of the Kubernetes cluster

```minikube ip```

The command shows 

```192.168.49.2```

and the port number is 
``` 30011 ```

### Retrieve data from the endpoint via RestApi 

- To retrieve data from the database via restapi call. The URL endpoint is composed by giving 

```http://192.168.49.2:30011/api/v1/customer/all```

The result from the GET command is the JSON response.

```
[
    {
        "customerId": 1,
        "name": "Renos",
        "surname": "Bardis",
        "balance": 100
    },
    {
        "customerId": 2,
        "name": "John",
        "surname": "Doe",
        "balance": 50
    },
    {
        "customerId": 3,
        "name": "Nick",
        "surname": "Smith",
        "balance": 45
    }
]
```
- Retrieve one record via restapi call again. The URL endpoint is composed by giving

```http://192.168.49.2:30011/api/v1/customer/1```

The result from the GET command is the JSON response.

```
{
    "customerId": 1,
    "name": "Renos",
    "surname": "Bardis",
    "balance": 100
}
```
