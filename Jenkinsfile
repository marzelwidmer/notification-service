pipeline {
  agent {
    docker {
      image 'maven:3.6-jdk-8-alpine'
      args '-v "$PWD":/usr/src/app'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh 'mvn clean'
      }
    }
  }
}