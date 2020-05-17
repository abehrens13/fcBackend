pipeline {
	agent any
    options {
    	timeout(time: 15, unit: 'MINUTES') 
    }

	environment {
    	DOCKERID = "feb18"
    	IMAGE = 'fcbackend'
    	VERSION = '0.0.1-SNAPSHOT'
  	}
  
	stages {
		stage('Show Tool Versions'){
			steps{
				sh 'mvn --version'
				sh 'docker --version'
				sh 'java -version'			    
			}
		}
	}
  
  	tools {
    	maven '/usr/local/bin/mvn'
    	jdk 'JDK8'
  	}
}
