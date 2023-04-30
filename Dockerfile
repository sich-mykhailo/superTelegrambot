FROM maven:3.8-amazoncorretto-17 AS build
COPY . .
RUN mvn clean package

FROM amazoncorretto:17-alpine-jdk
RUN apk update
RUN curl -s https://ngrok-agent.s3.amazonaws.com/ngrok.asc | sudo tee /etc/apt/trusted.gpg.d/ngrok.asc >/dev/null && echo "deb https://ngrok-agent.s3.amazonaws.com buster main" | sudo tee /etc/apt/sources.list.d/ngrok.list && sudo apt update && sudo apt install ngrok
RUN ngrok http 5050
COPY --from=build ./target/superpelegrambot-0.0.1-SNAPSHOT.jar ./superpelegrambot-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","./superpelegrambot-0.0.1-SNAPSHOT.jar"]