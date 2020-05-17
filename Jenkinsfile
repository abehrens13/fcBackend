pipeline {
	agent any
    options {
    	timeout(time: 15, unit: 'MINUTES') 
    }

	environment {
    	DOCKERID = "feb18"
    	IMAGE = readMavenPom().getArtifactId()
    	VERSION = readMavenPom().getVersion()
  	}
  
	stages {
		stage('Show Tool Versions'){
			sh 'mvn --version'
			sh 'docker --version'
			sh 'java -version'			    
		}
	}
  
  	tools {
    	maven '/usr/local/bin/mvn'
    	jdk 'JDK8'
  	}
}
