pipeline {
	agent any
    options {
    	timeout(time: 15, unit: 'MINUTES')
    	disableConcurrentBuilds() 
    }

	environment {
    	DOCKERID = "feb18"
    	IMAGE = 'fcbackend'
    	VERSION = '0.0.1-SNAPSHOT'
  	}
  
	stages {
		/**=======================*/
		stage('Show Tool Versions'){
			steps{
				sh 'mvn --version'
				sh 'docker --version'
				sh 'java -version'
				echo "PATH = ${PATH}"
                echo "M2_HOME = ${M2_HOME}"			    
			}
		}
		
		/**=======================*/
		stage('Maven Build') {
      		steps {
        		sh 'mvn -DskipTests clean package' 
      		}
    	}
    	
    	
		/**=======================*/
		stage('Unit Tests') {
      		steps {
        		sh 'mvn test' 
      		}
      		post {
        		success {
          			junit 'target/surefire-reports/**/*.xml' 
        		}
      		}    
		}
		
		/**=======================*/
		stage('Build Docker Image - All Branches') {
			when { branch pattern: "dev-.*", comparator: "REGEXP"}
      		steps {
        		sh """
          			docker login
          			docker build -t ${env.DOCKERID}/${env.IMAGE}:${env.VERSION} .
          			docker push ${env.DOCKERID}/${env.IMAGE}:${env.VERSION}
        		"""
      		}    
		}		
		
		/**=======================*/
		stage('Build Docker Image - Master Branch') {
      		when {
        		branch 'master'  //only run these steps on the master branch
      		}
      		steps {
        		sh """
          			docker login
          			docker build -t ${env.DOCKERID}/${env.IMAGE}:${env.VERSION} .
          			docker push ${env.DOCKERID}/${env.IMAGE}:${env.VERSION}
          			docker push ${env.DOCKERID}/${env.IMAGE}:latest
        		"""
      		}    
		}		
		
	}
  
  	tools {
    	maven '/usr/local/bin/mvn'
    	jdk 'JDK8'
  	}
}
