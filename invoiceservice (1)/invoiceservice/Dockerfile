FROM openjdk:13-jdk-alpine
ADD target/invoice-docker.jar invoice-docker.jar
VOLUME /tmp
EXPOSE 8081
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /invoice-docker.jar"]
