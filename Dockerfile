FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
ARG USER=1001
ARG APP_ROOT=application
WORKDIR ${APP_ROOT}
COPY --chown=${USER} . .
RUN mvn  clean package --no-transfer-progress --batch-mode
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar

FROM eclipse-temurin:21.0.1_12-jre-alpine AS run
ARG USER=1001
ARG APP_ROOT=application
WORKDIR ${APP_ROOT}
COPY --from=builder --chown=${USER} ${APP_ROOT}/app.jar app.jar

EXPOSE 8080
USER ${USER}
ENTRYPOINT ["java", "-jar", "app.jar"]