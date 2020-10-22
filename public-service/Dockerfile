#
# Build stage
#
FROM maven:3.6.3-jdk-11-slim AS build
COPY src /app/src
COPY pom.xml /app
RUN mvn -f /app/pom.xml clean install

#
# Run stage
#
FROM openjdk:11-jdk
VOLUME /tmp
COPY --from=build /app/target/*.jar app.jar
COPY application-docker.properties application-docker.properties
ENTRYPOINT ["java","-jar", "-Dspring.profiles.active=docker, dev", "-Dspring.config.additional-location=file:application-docker.properties", "/app.jar"]