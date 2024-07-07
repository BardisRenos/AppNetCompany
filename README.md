# AppNetCompany
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

  ### Application Properties

We are changing the server port from 8080 (Default) to 8088.

```
server:
  port: '8088'
```
The whole application.yml file consists of the configuration of the application regarding the memory database H2 and the hibernate configuration. 

### Docker

```
FROM openjdk:17-alpine
LABEL maintainer="Renard (Renos) Bardis"
EXPOSE 8089
ADD target/app-net-company.jar app-net-company.jar
CMD ["java", "-jar", "/app-net-company.jar"]
```

### Kubernetes

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

### Installing minikube
To deploy minikube is local Kubernetes, focusing on making it easy to learn and develop for Kubernetes.

This link has all instructions to [Install](https://minikube.sigs.k8s.io/docs/start/?arch=%2Flinux%2Fx86-64%2Fstable%2Fbinary+download#Service) minikube 
