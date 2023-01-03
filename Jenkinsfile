pipeline {
    agent any

    stages {
        stage('git repo & clean') {
            steps {
                sh "rm -f -R ./parserBot"
                sh "sudo mkdir /usr/bin/parserBot"
                sh "git -C /usr/bin/parserBot clone https://github.com/sich-mykhailo/superTelegrambot.git"
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