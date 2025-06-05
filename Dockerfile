FROM eclipse-temurin:17.0.15_6-jre-alpine-3.21
LABEL authors="mrinal"
WORKDIR /app
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8081
ENTRYPOINT ["java", "-jar", "app.jar"]