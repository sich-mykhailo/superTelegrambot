pipeline {
      agent any
       tools {
        maven "MAVEN"
        jdk "JDK"
      }
      stages {
      stage('Build') {
      steps {
      echo "Start"
      dir("/var/lib/jenkins/workspace/demopipelinetask/my-app") {
                      sh 'mvn -B -DskipTests clean package'
                      }
      echo "End"
      }
    }
    stage('Test') {

    }
    stage('Deploy') {

    }
  }
}