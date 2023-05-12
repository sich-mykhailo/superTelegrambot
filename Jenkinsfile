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
          echo " \n
                services: \n
                  app: \n
                    image: super-telegram-bot:latest \n
                    restart: always \n
                    build: . \n
                    ports: \n
                      - \"5050:5050\" \n
                    depends_on: \n
                      - ng_rok \n
                    environment: \n
                      SPRING_APPLICATION_JSON: \'{ \n
                        \"spring.jpa.hibernate.ddl-auto\" : \"none\", \n
                        \"spring.datasource.url\" : \"${DB_URL}\", \n
                        \"spring.datasource.username\" : \"${DB_USER_NAME}\", \n
                        \"spring.datasource.password" : \"${DB_PASSWORD}\", \n
                        \"spring.jpa.database-platform\" : \"org.hibernate.dialect.PostgreSQL94Dialect\", \n
                        \"spring.datasource.driver-class-name\" : \"org.postgresql.Driver\", \n
                        \"bot.username\" : \"${TELEGRAM_BOT_USER_NAME}\", \n
                        \"bot.token\" : \"${TELEGRAM_BOT_TOKEN}\", \n
                        \"server.port\" : \"5050\", \n
                        \"notification.email.admin\" : \"${TELEGRAM_ADMIN_EMAIL}\", \n
                        \"bot.address.help\" : \"${TELEGRAM_HELP_EMAIL}\", \n
                        \"cloud.aws.credentials.access-key\" : \"${AWS_ACCESS_KEY}\", \n
                        \"cloud.aws.credentials.secret-key\" : \"${AWS_SECRET_KEY}\", \n
                        \"cloud.aws.region.static\" : \"us-east-1\", \n
                        \"cloud.aws.region.auto\" : \"false\", \n
                        \"cloud.aws.stack.auto\" : \"false\", \n
                        \"cloud.aws.end-point.uri\" : \"${AWS_SQS_URI}\", \n
                        \"spring.mail.host\" : \"smtp.gmail.com\", \n
                        \"spring.mail.port\" : \"587\", \n
                        \"spring.mail.username\" : \"${MAIL_USER_NAME}\", \n
                        \"spring.mail.password\" : \"${MAIL_PASSWORD}\", \n
                        \"spring.mail.properties.mail.smtp.auth\" : \"true\", \n
                        \"spring.mail.properties.mail.smtp.starttls.enable\" : \"true\" \n
                      }\' \n
                    container_name: bot-container \n
\n
                  ng_rok: \n
                    image: ngrok/ngrok \n
                    restart: always \n
                    env_file: .env \n
                    ports: \n
                      - \"4040:4040\" \n
                    environment: \n
                      - NGROK_AUTHTOKEN=${NGROK_TOKEN} \n
                    container_name: ng_rok_container \n
                    command: http bot-container:5050 \n
          " >> docker-compose.yml'
      sh '${CONNECT_TO_REMOTE_SERVER} \
          docker-compose up'
      }
    }
  }
}
