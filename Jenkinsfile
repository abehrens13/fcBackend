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
		//https://igorski.co/sonarqube-scans-using-jenkins-declarative-pipelines/
		stage('SonarCloud') {
  			environment {
    			SCANNER_HOME = tool 'MySonarQubeScanner'
    			PROJECT_NAME = "igorstojanovski_jenkins-pipeline-as-code"
			}
  			steps {
    			withSonarQubeEnv('Sonar') {
        			sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.java.binaries=build/classes/java/ -Dsonar.projectKey=${env.IMAGE} -Dsonar.sources=.'''
    			}
  			}
		}		
		
		stage('Sonarqube') {
    		environment {
        		scannerHome = tool 'docker-sonar'
    		}
    		steps {
        		withSonarQubeEnv('My SonarQube Server') {
            		sh "${scannerHome}/bin/sonar-scanner"
        		}
        		timeout(time: 10, unit: 'MINUTES') {
            		waitForQualityGate abortPipeline: true
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
