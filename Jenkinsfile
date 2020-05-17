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
	}
  
  	tools {
    	maven '/usr/local/bin/mvn'
    	jdk 'JDK8'
  	}
}
