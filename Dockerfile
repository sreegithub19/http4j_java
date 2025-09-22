# Stage 1: Build the project with Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code
COPY src ./src

# Build the project (package jar)
RUN mvn clean package -DskipTests

# Stage 2: Run the project with OpenJDK
FROM eclipse-temurin:21-jdk

WORKDIR /app

# Copy jar from build stage
COPY --from=build /app/target/http4j-hello-1.0-SNAPSHOT.jar ./app.jar

# Expose port
EXPOSE 8080

# Run the server
CMD ["java", "-cp", "app.jar", "com.example.SimpleHttpServer"]
