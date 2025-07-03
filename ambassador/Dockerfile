# Start from a Node.js 20 (LTS) image 1
FROM openjdk:21-jdk-slim

# Specify the directory inside the image in which all commands will run 2
WORKDIR /usr/app

# Copy all of the app files into the image 4
COPY target/*.jar app.jar

EXPOSE 8080

# The default command to run when starting the container 5
CMD [ "java", "-jar", "app.jar" ]