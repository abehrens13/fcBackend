pipeline {
  agent { dockerfile true }
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
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
