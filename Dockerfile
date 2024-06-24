FROM openjdk:17-alpine
LABEL maintainer="Renard (Renos) Bardis"
EXPOSE 8089
ADD target/app-net-company.jar app-net-company.jar
CMD ["java", "-jar", "/app-net-company.jar"]