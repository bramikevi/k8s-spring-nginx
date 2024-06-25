# Use the official OpenJDK 17 image as the base image
FROM openjdk:17-alpine

# Set the working directory in the container
WORKDIR /app

# Copy the built jar file into the container
COPY target/k8s-spring-nginx-0.0.1-SNAPSHOT.jar k8s-spring-nginx-0.0.1-SNAPSHOT.jar

# Expose the port the application runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "k8s-spring-nginx-0.0.1-SNAPSHOT.jar"]
