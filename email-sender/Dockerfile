FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/email-sender-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} email-sender.jar
ENTRYPOINT ["java", "-jar", "/email-sender.jar"]