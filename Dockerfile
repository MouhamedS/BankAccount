ARG USER=1001
FROM maven:3.9.6-eclipse-temurin-21-alpine as builder
WORKDIR application
COPY --chown=${USER} . .
RUN mvn  clean package --no-transfer-progress --batch-mode
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
RUN java -Djarmode=layertools -jar app.jar extract
#ENTRYPOINT ["java", "-jar", "/app.jar"]

FROM eclipse-temurin:21.0.1_12-jre-alpine
WORKDIR application
COPY --from=builder application/dependencies/ ./
COPY --from=builder application/spring-boot-loader/ ./
COPY --from=builder application/snapshot-dependencies/ ./
COPY --from=builder application/application/ ./

EXPOSE 8080
USER ${USER}
ENTRYPOINT ["java", "org.springframework.boot.loader.launch.JarLauncher"]