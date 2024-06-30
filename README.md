# AppNetCompany
# Assignment for Personal Development - Net-Company


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
The whole application.yml file consists configuration of the application regarding the memory database H2 and the hibernate configuration. 

### Docker

```
FROM openjdk:17-alpine
LABEL maintainer="Renard (Renos) Bardis"
EXPOSE 8089
ADD target/app-net-company.jar app-net-company.jar
CMD ["java", "-jar", "/app-net-company.jar"]
```
