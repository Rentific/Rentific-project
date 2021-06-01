FROM openjdk:13-jdk-alpine
ADD target/authentication-docker.jar authentication-docker.jar
EXPOSE 8090
ENTRYPOINT ["java", "-jar", "authentication-docker.jar"]
