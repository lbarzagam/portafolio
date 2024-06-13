FROM eclipse-temurin:8

MAINTAINER lbarzagam11@gmail.com

COPY target/backend-0.0.1.jar app.jar

ENTRYPOINT ["java","-jar","/app.jar"]


#FROM openjdk:8-jdk-alpine

#MAINTAINER lbarzagam11@gmail.com

#COPY src/main/resources/application.properties app.jar

#RUN mkdir -p /app/logs

#EXPOSE 8080

#ENTRYPOINT ["java","-jar","/app.jar"]
