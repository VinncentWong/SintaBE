FROM openjdk:latest AS build
LABEL authors="Vinncent Alexander Wong"
COPY . .
RUN ./mvnw clean package -DskipTests=true

FROM eclipse-temurin:20.0.1_9-jre
COPY --from=build ./target/sinta-0.0.1-SNAPSHOT.jar .
COPY ./src/main/resources/application-docker.properties .
ENTRYPOINT ["java", "-jar", "sinta-0.0.1-SNAPSHOT.jar", "--spring.config.location=application-docker.properties"]