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
		stage('SonarQube analysis') {
  			withSonarQubeEnv('MySonarQubeScanner') {
                  sh 'mvn sonar:sonar'
            }
		}

        stage("Quality Gate"){
            timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
                def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
                if (qg.status != 'OK') {
                    error "Pipeline aborted due to quality gate failure: ${qg.status}"
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
					docker tag ${env.DOCKERID}/${env.IMAGE}:${env.VERSION} ${env.DOCKERID}/${env.IMAGE}:latest
          			docker push ${env.DOCKERID}/${env.IMAGE}:${env.VERSION}
          			docker push ${env.DOCKERID}/${env.IMAGE}:latest
        		"""
      		}
		}

	}
}
