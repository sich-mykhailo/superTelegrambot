pipeline {
    agent any

    stages {
        stage('git repo & clean') {
            steps {
                //sh "rmdir  /s /q superTelegrambot"
                sh "git clone https://github.com/sich-mykhailo/superTelegrambot.git"
                sh "mvn clean -f superTelegrambot"
            }
        }
        stage('Install') {
            steps {
                sh "mvn install -f superTelegrambot"
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