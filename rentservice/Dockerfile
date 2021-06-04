FROM openjdk:13-jdk-alpine
ADD target/rent-docker.jar rent-docker.jar
VOLUME /tmp
EXPOSE 8080
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /rent-docker.jar"]



