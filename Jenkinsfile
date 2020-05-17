pipeline {
  agent none
    agent any
    options {
        timeout(time: 15, unit: 'MINUTES') 
    }

  environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    IMAGE = readMavenPom().getArtifactId()
    VERSION = readMavenPom().getVersion()
  }
  
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
    /**
    https://tutorials.releaseworksacademy.com/learn/building-your-first-docker-image-with-jenkins-2-guide-for-developers
    https://blog.csuttles.io/using-jenkins-and-aws-to-build-and-push-docker-images/
    */
    
	stage('Build image') {
            steps {
                echo 'Starting to build docker image'

                script {
                    def customImage = docker.build("${env.IMAGE}:${env.VERSION}")
                    customImage.push()
                }
            }
        }
    

    
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
