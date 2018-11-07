pipeline {
  agent {
    docker {
      image 'gradle:4.10-jre8-slim'
    }

  }
  stages {
    stage('Initialize') {
      steps {
        sh './gradlew clean'
      }
    }
    stage('Build') {
      steps {
        sh './gradlew build'
      }
    }
    stage('Test') {
      steps {
        sh './gradlew test'
      }
    }
  }
}