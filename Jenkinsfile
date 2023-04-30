pipeline {
  agent any
  environment {
    PORT="${PORT_5050}"
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
  }

  stages {
    stage('Delete old image') {
      steps {
        sh '${CONNECT_TO_REMOTE_SERVER} docker stop super-telegram-bot || true && docker rm super-telegram-bot || true'
        sh '${CONNECT_TO_REMOTE_SERVER} docker rmi super-telegram-bot:latest --force || true'
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
          docker run -e AWS_ACCESS_KEY=${AWS_ACCESS_KEY} \
                          -e AWS_SECRET_KEY=${AWS_SECRET_KEY} \
                          -e AWS_SQS_URI=${AWS_SQS_URI} \
                          -e DB_PASSWORD=${DB_PASSWORD} \
                          -e DB_URL=${DB_URL} \
                          -e DB_USER_NAME=${DB_USER_NAME} \
                          -e MAIL_PASSWORD=${MAIL_PASSWORD} \
                          -e MAIL_USER_NAME=${MAIL_USER_NAME} \
                          -e TELEGRAM_ADMIN_EMAIL=${TELEGRAM_ADMIN_EMAIL} \
                          -e TELEGRAM_BOT_TOKEN=${TELEGRAM_BOT_TOKEN} \
                          -e TELEGRAM_BOT_USER_NAME=${TELEGRAM_BOT_USER_NAME} \
                          -e TELEGRAM_HELP_EMAIL=${TELEGRAM_HELP_EMAIL} -d --name super-telegram-bot -p ${PORT}:${PORT} super-telegram-bot:latest'
      }
    }
  }
}
