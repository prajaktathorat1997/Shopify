# Use the official OpenJDK 8 image from Docker Hub
FROM openjdk:8

# Set the working directory in the container
WORKDIR /app

# Copy the current directory contents into the container at /app
COPY ./target/shopify.jar .

# Define the entry point for the container
CMD ["java", "-jar", "shopify.jar"]
