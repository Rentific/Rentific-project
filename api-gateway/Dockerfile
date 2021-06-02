FROM openjdk:13-jdk-alpine
ADD target/gateway-docker.jar gateway-docker.jar
EXPOSE 8762
VOLUME /tmp
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /gateway-docker.jar" ]
