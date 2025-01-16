FROM gradle:8.4-jdk17 AS builder
WORKDIR /app
COPY . .
USER root
RUN gradle clean --no-daemon --stacktrace --info
RUN gradle build --no-daemon --stacktrace --info -x test
RUN chown -R gradle:gradle /home/gradle/.gradle
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]