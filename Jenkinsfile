pipeline {
  agent {
    docker {
      image 'gradle:4.10-jre8-slim'
      args '-v "$PWD":/usr/src/app'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh './gradlew clean'
      }
    }
  }
}