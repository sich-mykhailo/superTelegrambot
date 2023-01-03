pipeline {
    agent any

    stages {
        stage('git repo & clean') {
            steps {
                sh "git clone https://github.com/sich-mykhailo/superTelegrambot.git"
                sh "mvn clean package"
            }
        }
        stage('Test') {
            steps {
                sh "mvn test -f superTelegrambot"
            }
        }
          stage('Package') {
                    steps {
                        sh "mvn package -f superTelegrambot"
                    }
          }
    }
}