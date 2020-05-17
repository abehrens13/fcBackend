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
    stage('Test Tools') {
      steps {
        sh '''
                  echo "PATH = ${PATH}"
                  echo "M2_HOME = ${M2_HOME}"
                '''
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

	stage('Build and Publish Image master') {
      when {
        branch 'master'  //only run these steps on the master branch
      }
      steps {
        /*
         * Multiline strings can be used for larger scripts. It is also possible to put scripts in your shared library
         * and load them with 'libaryResource'
         */
        sh """
          docker build -t ${IMAGE} .
          docker tag ${IMAGE} ${IMAGE}:${VERSION}
          docker push ${IMAGE}:${VERSION}
        """
      }
    }    
	stage('Build and Publish Image dev') {
      when {
        branch 'dev-0.0.1'  //only run these steps on the master branch
      }
      steps {
        /*
         * Multiline strings can be used for larger scripts. It is also possible to put scripts in your shared library
         * and load them with 'libaryResource'
         "${env.IMAGE}:${env.VERSION}"
         */
        sh """
          docker build -t ${env.IMAGE} .
          docker tag ${env.IMAGE} ${env.IMAGE}:${env.VERSION}
          docker push ${env.IMAGE}:${env.VERSION}
        """
      }    
	}
    
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
