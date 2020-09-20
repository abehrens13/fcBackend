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
		    environment {
                SCANNER_HOME = tool 'SonarQubeScanner'
                ORGANIZATION = "openaqua"
                PROJECT_NAME = "fcBackend"
            }
            steps {
                withSonarQubeEnv('MySonarQubeScanner') {
                    sh '''$SCANNER_HOME/bin/sonar-scanner  \
                    -Dsonar.java.binaries=build/classes/java/ \
                    -Dsonar.projectKey=$PROJECT_NAME \
                    -Dsonar.login=“admin” -Dsonar.password=“admin” \
                    -Dsonar.sources=.'''
                }
            }
		}
        stage("Quality Gate") {
            steps {
                timeout(time: 1, unit: 'MINUTES') {
                    waitForQualityGate abortPipeline: true
                }
            }
        }


        //docker is broken. Here is a better way: https://www.edureka.co/community/55640/jenkins-docker-docker-image-jenkins-pipeline-docker-registry
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
