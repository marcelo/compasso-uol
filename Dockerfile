FROM openjdk:8-jdk-alpine

ARG JAR_FILE=target/productms-0.0.1-SNAPSHOT.jar

WORKDIR /opt/app

COPY ${JAR_FILE} productms.jar

ENTRYPOINT ["java","-jar","productms.jar"]
