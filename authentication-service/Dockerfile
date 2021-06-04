FROM openjdk:13-jdk-alpine
ADD target/authentication-docker.jar authentication-docker.jar
EXPOSE 8090
VOLUME /tmp
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /authentication-docker.jar" ]
