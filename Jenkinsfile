pipeline {
  agent any
  stages {
    stage('Stage Hello World') {
      steps {
        echo 'Hello World!'
      }
    }

    stage('Initialize') {
      steps {
        sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -Dmaven.test.failure.ignore=true install' 
      }
      post {
        success {
          junit 'target/surefire-reports/**/*.xml' 
        }
      }    
    }
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
