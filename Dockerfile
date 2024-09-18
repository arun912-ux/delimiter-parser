FROM eclipse-temurin:17-jdk-alpine AS build

USER 1000

WORKDIR /app

COPY . .

RUN ./gradlew clean build -x test


FROM eclipse-temurin:17-jre-alpine

USER 1000

WORKDIR /app

COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
