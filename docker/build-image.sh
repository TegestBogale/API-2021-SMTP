
#!/bin/bash

# Ask maven to build the executable jar file from the source files
mvn clean install --file ../Labo_4_SMTP/pom.xml

# Copy the executable jar file in the current directory
CP ../target/Labo_4_SMTP-1.0-SNAPSHOT.jar
# Build the Docker image locally
docker build --tag Tegest/Labo_4_SMTP-docker-mailRobot .
