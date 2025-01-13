FROM openjdk:21-jdk as build-image
WORKDIR /app
COPY . .

RUN ./mvnw clean package

FROM openjdk:21
WORKDIR /app
COPY --from=build-image /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app/app.jar"]