pipeline {
    agent any
    options {
    	timeout(time: 15, unit: 'MINUTES')
    	disableConcurrentBuilds()
    }

	environment {
    	DOCKERID = "feb18"
    	IMAGE = 'fcbackend'
    	VERSION = '0.0.2-SNAPSHOT'
        registry = "feb18/fcbackend"
        registryCredential = 'dockerlogin'
        dockerImage = ''
        }
  	}

	stages {
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
		stage('Maven Install') {
      		steps {
        		sh 'mvn install'
      		}
		}

		/**=======================*/
		//https://igorski.co/sonarqube-scans-using-jenkins-declarative-pipelines/
		//sonar-scanner must be enabled in jenkins
		//user/password must be defined in sonarqube
		//and the login musst be configured in jenkins
		stage('SonarQube analysis') {
		    environment {
                SCANNER_HOME = tool 'SonarQubeScanner'
                ORGANIZATION = "openaqua"
                PROJECT_NAME = "fcBackend"
            }
            steps {
                withSonarQubeEnv('MySonarQubeScanner') {
                    sh '''$SCANNER_HOME/bin/sonar-scanner  \
                    -Dsonar.java.binaries=target/classes/ \
                    -Dsonar.projectKey=$PROJECT_NAME \
                    -Dsonar.sources=.'''
                }
            }
		}

		//quality gates fails
        //stage("Quality Gate") {
        //    steps {
        //        timeout(time: 1, unit: 'MINUTES') {
        //            waitForQualityGate abortPipeline: true
        //        }
        //    }
        //}

	}
}
