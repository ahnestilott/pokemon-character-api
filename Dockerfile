# Stage 1: Build the application using Java 25
FROM eclipse-temurin:25-jdk AS build

WORKDIR /app

# Copy the Maven wrapper and project files
COPY .mvn .mvn
COPY mvnw pom.xml ./

# Give the Maven wrapper permission to run
RUN chmod +x mvnw

# Copy the source code
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:25-jre

WORKDIR /app

# Copy the built JAR from the build stage
COPY --from=build /app/target/*.jar app.jar

# Start the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]