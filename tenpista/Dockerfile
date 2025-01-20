# Use Maven image with JDK 23 to build the project
FROM maven:3.9.9-eclipse-temurin-23 AS build

# Set the working directory
WORKDIR /app

# Copy the Maven configuration and all source code
COPY pom.xml ./
COPY ./tenpista-api ./tenpista-api
COPY ./tenpista-domain ./tenpista-domain
COPY ./tenpista-dto ./tenpista-dto
COPY ./tenpista-persistence ./tenpista-persistence
COPY ./tenpista-service ./tenpista-service

# Build the application
RUN mvn clean package -DskipTests

# Use OpenJDK 23 as runtime environment
FROM openjdk:23-slim

# Set the working directory for the runtime
WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /app/tenpista-api/target/tenpista-api-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
