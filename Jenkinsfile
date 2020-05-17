pipeline {
    agent any
    options {
        timeout(time: 15, unit: 'MINUTES') 
    }

  environment {
    //Use Pipeline Utility Steps plugin to read information from pom.xml into env variables
    //IMAGE = readMavenPom().getArtifactId()
    //VERSION = readMavenPom().getVersion()
    IMAGE = "fcbackend"
    VERSION = "0.0.1-SNAPSHOT"
  }
  
  stages {
    def customImage 
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
	stage('Build image') {
            steps {
                echo 'Starting to build docker image'
				customImage = docker.build("${env.IMAGE}:${env.VERSION}")
                }
    }
    
	stage('Push image') {
            steps {
                echo 'Push docker image'

                steps {
                	docker.withRegistry('https://hub.docker.com/', 'dockerhub-credential') {
                    	customImage.push()
                    }
            }
        }
	}
    
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
