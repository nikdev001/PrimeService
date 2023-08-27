# Start with a base image containing Java runtime (Java 17)
FROM openjdk:17.0.1-jdk-slim

# The application's jar file
ARG JAR_FILE=target/*.jar

# Copy the application's jar to the container image
COPY ${JAR_FILE} app.jar

# Specify the default command to run on container start
ENTRYPOINT ["java","-jar","/app.jar"]