FROM openjdk:13-jdk-alpine
ADD target/rent-docker.jar rent-docker.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "rent-docker.jar"]
