FROM openjdk:17.0.2-jdk-slim-buster
ARG JAR_FILE=target/scheduler-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} scheduler.jar
ENTRYPOINT ["java", "-jar", "/scheduler.jar"]