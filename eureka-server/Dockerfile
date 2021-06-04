FROM openjdk:13-jdk-alpine
ADD target/eureka-docker.jar eureka-docker.jar
EXPOSE 8761
VOLUME /tmp
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /eureka-docker.jar" ]
