pipeline {
    agent any
     environment {
        PORT="${PORT_5050}"
        PARSER_ADMIN_EMAIL="${PARSER_ADMIN_EMAIL}"
        AWS_ACCESS_KEY="${AWS_ACCESS_KEY}"
        AWS_SECRET_KEY="${AWS_SECRET_KEY}"
        AWS_SQS_URI="${AWS_SQS_URI}"
        OLX_TOKEN="${OLX_TOKEN}"
        NGROK_TOKEN="${NGROK_TOKEN}"
        DB_PASSWORD="${DB_PASSWORD}"
        DB_URL="${DB_URL}"
        DB_USER_NAME="${DB_USER_NAME}"
        MAIL_PASSWORD="${MAIL_PASSWORD}"
        MAIL_USER_NAME="${MAIL_USER_NAME}"
        TELEGRAM_ADMIN_EMAIL="${TELEGRAM_ADMIN_EMAIL}"
        TELEGRAM_BOT_TOKEN="${TELEGRAM_BOT_TOKEN}"
        TELEGRAM_BOT_USER_NAME="${TELEGRAM_BOT_USER_NAME}"
        TELEGRAM_HELP_EMAIL="${TELEGRAM_HELP_EMAIL}"
    }
    tools {
    maven 'MAVEN'
    }

    stages {
     stage('Delete old image') {
       steps {
         sh sudo docker ps -a
         sh sudo docker stop container
         sh sudo docker rm container
         sh sudo docker rmi super-telegram-bot:latest
       }
     }
      stage('Adjust NgRok') {
        steps {
          sh sudo "start to create https endpoint"
          sh sudo docker run -it -e NGROK_AUTHTOKEN=${NGROK_TOKEN} -p 4040:4040 ngrok/ngrok http ${PORT}
        }
      }
       stage('Build Docker Image') {
            steps {
            sh sudo echo "build.."
            sh sudo docker build https://github.com/sich-mykhailo/superTelegrambot.git -t super-telegram-bot:latest
            }
        }
        stage ('Deploy') {
            steps {
            sh sudo docker run -e AWS_ACCESS_KEY=${AWS_ACCESS_KEY} -e AWS_SECRET_KEY=${AWS_SECRET_KEY} -e AWS_SQS_URI=${AWS_SQS_URI} \
            -e DB_PASSWORD=${DB_PASSWORD} -e DB_URL=${DB_URL} -e DB_USER_NAME=${DB_USER_NAME} -e MAIL_PASSWORD=${MAIL_PASSWORD} \
            -e MAIL_USER_NAME=${MAIL_USER_NAME} -e TELEGRAM_ADMIN_EMAIL={TELEGRAM_ADMIN_EMAIL} -e TELEGRAM_BOT_TOKEN=${TELEGRAM_BOT_TOKEN} \
            -e TELEGRAM_BOT_USER_NAME=${TELEGRAM_BOT_USER_NAME} \
            -e TELEGRAM_HELP_EMAIL=${TELEGRAM_HELP_EMAIL} -d --name container -p ${PORT}:${PORT} super-telegram-bot:latest
            }
        }
    }
}
