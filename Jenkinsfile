pipeline {
  agent any
  stages {
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
        sh 'mvn -DskipTests clean package' 
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test' 
      }
      post {
        success {
          junit 'target/surefire-reports/**/*.xml' 
        }
      }    
    }
    stage('Create Docker Images') {
      steps {
        sh '/usr/bin/python3  jenkins/dockerize.py' 
      }
    }
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
    python3 '/usr/bin/python3'
  }
}
