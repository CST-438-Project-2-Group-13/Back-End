# Use an official OpenJDK runtime as a parent image
FROM eclipse-temurin:23-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot jar file into the container and rename to app.jar
COPY build/libs/Wishlist-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080
EXPOSE 8080

# Command to run the jar file
ENTRYPOINT ["java", "-jar", "app.jar", "--server.port=${PORT:-8080}"]

