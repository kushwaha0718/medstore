# -----------------------------
# Stage 1: Build the application
# -----------------------------
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

# Copy everything
COPY . .

# Build the project
RUN mvn clean package -DskipTests

# -----------------------------
# Stage 2: Run the application
# -----------------------------
FROM eclipse-temurin:17-jdk-alpine

WORKDIR /app

# Copy built jar from stage 1
COPY --from=build /app/target/*.jar app.jar

# Expose (Render sets PORT env variable at runtime)
EXPOSE 8080

# Run using Render's PORT
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]