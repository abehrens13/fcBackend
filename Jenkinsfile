pipeline {
    agent any 
    tools { 
        maven 'Maven 3.6.1' 
        jdk 'jdk8' 
    }
    stages {
        stage('Stage Hello World') {
            steps {
                echo 'Hello world!' 
            }
        }
        
        stage ('Initialize') {
            steps {
                sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                ''' 
            }
        }

        stage ('Build') {
            steps {
                echo 'This is a minimal pipeline.' 
            }
        }
        
    }
}