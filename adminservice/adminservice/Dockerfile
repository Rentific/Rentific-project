FROM openjdk:13-jdk-alpine
ADD target/admin-docker.jar admin-docker.jar
VOLUME /tmp
EXPOSE 8083
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /admin-docker.jar"]
