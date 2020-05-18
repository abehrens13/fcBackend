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
		stage('Maven Install') {
      		steps {
        		sh 'mvn install'
      		}
		}

		/**=======================*/
		//https://igorski.co/sonarqube-scans-using-jenkins-declarative-pipelines/
		stage('SonarCloud') {
  			environment {
    			SCANNER_HOME = tool 'MySonarQubeScanner'
			}
  			steps {
    			withSonarQubeEnv('Sonar') {
        			sh '''$SCANNER_HOME/bin/sonar-scanner -Dsonar.java.binaries=target/classes -Dsonar.projectKey=${IMAGE} -Dsonar.sources=.'''
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
					docker tag ${env.DOCKERID}/${env.IMAGE}:${env.VERSION} ${env.DOCKERID}/${env.IMAGE}:latest .
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
