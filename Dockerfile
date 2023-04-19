FROM openjdk:17-alpine
VOLUME /tmp
ARG JAR_FILE=target/*.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java", "jar", "/app.jar"]