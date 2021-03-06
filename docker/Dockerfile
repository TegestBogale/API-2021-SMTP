FROM openjdk:11
MAINTAINER Tegest Bogale <tegest.bogale@heig-vd.ch>

# Install netcat, so that we can demonstrate container-to-container communication
RUN apt-get update && \
  apt-get -y install netcat && \
  apt-get clean

#
# When we build the image, we copy the executable jar in the image file system.
#
COPY MockMock.jar /opt/app/MockMock.jar

#
# Our application will accept TCP connections on port 2525. Note that the EXPOSE statement
# does not make the container accessible via the host. For the container to really be accessible,
# we must either use the -p or the -P flag when using the docker run command. With -p, we can
# specify an explicit port mapping (and EXPOSE is not even required). With -P, we let Docker
# assign random ports for each EXPOSEd port. We can then use the docker port command to know the port
# numbers that have been selected.
#
EXPOSE 8281 8282

#
# This is the command that is executed when the Docker container starts
#
CMD ["java", "-jar", "/opt/app/MockMock.jar", "-p", "2525"]