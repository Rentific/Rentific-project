FROM openjdk:13-jdk-alpine
ADD target/user-docker.jar user-docker.jar
VOLUME /tmp
EXPOSE 8082
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /user-docker.jar"]
