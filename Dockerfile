FROM maven:3.9.6-eclipse-temurin-21-alpine AS builder
ARG USER=1001
ARG APP_ROOT=application
WORKDIR ${APP_ROOT}
COPY --chown=${USER} . .
RUN mvn  clean package --no-transfer-progress --batch-mode
ARG JAR_FILE=target/*.jar
COPY --chown=${USER} ${JAR_FILE} app.jar
COPY --chown=${USER} target/otel/*.jar opentelemetry-javaagent.jar

FROM eclipse-temurin:21.0.2_13-jre-alpine AS run
ARG USER=1001
ARG APP_ROOT=application
WORKDIR ${APP_ROOT}
COPY --from=builder --chown=${USER} ${APP_ROOT}/app.jar app.jar
COPY --from=builder --chown=${USER} ${APP_ROOT}/opentelemetry-javaagent.jar opentelemetry-javaagent.jar

EXPOSE 8080
USER ${USER}
ENTRYPOINT ["java", "-javaagent:opentelemetry-javaagent.jar","-jar", "app.jar"]