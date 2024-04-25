FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/rest-service-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} rest-service.jar
ENTRYPOINT ["java", "-jar", "/rest-service.jar"]