# Use Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set working directory
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN ./mvnw dependency:go-offline || true

# Copy source code
COPY . .

# Build the application
RUN ./mvnw clean package -DskipTests

# Expose port (Render provides PORT dynamically)
EXPOSE 8080

# Run the app
CMD ["java", "-jar", "target/*.jar"]
