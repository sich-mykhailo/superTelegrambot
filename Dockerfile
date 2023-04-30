FROM maven:3.8-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk
RUN apk update
RUN apk add --no-cache curl
COPY --from=build ./target/superpelegrambot-0.0.1-SNAPSHOT.jar ./superpelegrambot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./superpelegrambot-0.0.1-SNAPSHOT.jar"]