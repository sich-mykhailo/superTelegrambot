pipeline {
  agent any
  environment {
    PORT="${PORT}"
    PARSER_ADMIN_EMAIL="${PARSER_ADMIN_EMAIL}"
    AWS_ACCESS_KEY="${AWS_ACCESS_KEY}"
    AWS_SECRET_KEY="${AWS_SECRET_KEY}"
    AWS_SQS_URI="${AWS_SQS_URI}"
    OLX_TOKEN="${OLX_TOKEN}"
    DB_PASSWORD="${DB_PASSWORD}"
    DB_URL="${DB_URL}"
    DB_USER_NAME="${DB_USER_NAME}"
    MAIL_PASSWORD="${MAIL_PASSWORD}"
    MAIL_USER_NAME="${MAIL_USER_NAME}"
    TELEGRAM_ADMIN_EMAIL="${TELEGRAM_ADMIN_EMAIL}"
    TELEGRAM_BOT_TOKEN="${TELEGRAM_BOT_TOKEN}"
    TELEGRAM_BOT_USER_NAME="${TELEGRAM_BOT_USER_NAME}"
    TELEGRAM_HELP_EMAIL="${TELEGRAM_HELP_EMAIL}"
    CONNECT_TO_REMOTE_SERVER="${CONNECT_TO_REMOTE_SERVER}"
    NGROK_TOKEN="${NGROK_TOKEN}"
  }

  stages {
    stage('Delete old image') {
      steps {
        sh '${CONNECT_TO_REMOTE_SERVER} docker stop super-telegram-bot || true'
        sh '${CONNECT_TO_REMOTE_SERVER} docker rm super-telegram-bot || true'
        sh '${CONNECT_TO_REMOTE_SERVER} docker rmi super-telegram-bot:latest || true'
      }
    }

    stage('Build Docker Image') {
      steps {
        sh 'echo "build.."'
        sh '${CONNECT_TO_REMOTE_SERVER} \
            docker build https://github.com/sich-mykhailo/superTelegrambot.git -t super-telegram-bot:latest'
      }
    }

    stage ('Deploy') {
      steps {
      sh '${CONNECT_TO_REMOTE_SERVER} \
          touch docker-compose.yml'
      sh '${CONNECT_TO_REMOTE_SERVER} \
          echo " \
                services: \
                  app: \
                    image: super-telegram-bot:latest \
                    restart: always \
                    build: . \
                    ports: \
                      - \"5050:5050\" \
                    depends_on: \
                      - ng_rok \
                    environment: \
                      SPRING_APPLICATION_JSON: \'{ \
                        \"spring.jpa.hibernate.ddl-auto\" : \"none\", \
                        \"spring.datasource.url\" : \"${DB_URL}\", \
                        \"spring.datasource.username\" : \"${DB_USER_NAME}\", \
                        \"spring.datasource.password" : \"${DB_PASSWORD}\", \
                        \"spring.jpa.database-platform\" : \"org.hibernate.dialect.PostgreSQL94Dialect\", \
                        \"spring.datasource.driver-class-name\" : \"org.postgresql.Driver\", \
                        \"bot.username\" : \"${TELEGRAM_BOT_USER_NAME}\", \
                        \"bot.token\" : \"${TELEGRAM_BOT_TOKEN}\", \
                        \"server.port\" : \"5050\", \
                        \"notification.email.admin\" : \"${TELEGRAM_ADMIN_EMAIL}\", \
                        \"bot.address.help\" : \"${TELEGRAM_HELP_EMAIL}\", \
                        \"cloud.aws.credentials.access-key\" : \"${AWS_ACCESS_KEY}\", \
                        \"cloud.aws.credentials.secret-key\" : \"${AWS_SECRET_KEY}\", \
                        \"cloud.aws.region.static\" : \"us-east-1\", \
                        \"cloud.aws.region.auto\" : \"false\", \
                        \"cloud.aws.stack.auto\" : \"false\", \
                        \"cloud.aws.end-point.uri\" : \"${AWS_SQS_URI}\", \
                        \"spring.mail.host\" : \"smtp.gmail.com\", \
                        \"spring.mail.port\" : \"587\", \
                        \"spring.mail.username\" : \"${MAIL_USER_NAME}\", \
                        \"spring.mail.password\" : \"${MAIL_PASSWORD}\", \
                        \"spring.mail.properties.mail.smtp.auth\" : \"true\", \
                        \"spring.mail.properties.mail.smtp.starttls.enable\" : \"true\" \
                      }\' \
                    container_name: bot-container \
\
                  ng_rok: \
                    image: ngrok/ngrok \
                    restart: always \
                    env_file: .env \
                    ports: \
                      - \"4040:4040\" \
                    environment: \
                      - NGROK_AUTHTOKEN=${NGROK_TOKEN} \
                    container_name: ng_rok_container \
                    command: http bot-container:5050 \
          " >> docker-compose.yml'
      sh '${CONNECT_TO_REMOTE_SERVER} \
          docker-compose up'
      }
    }
  }
}
