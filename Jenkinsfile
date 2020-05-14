pipeline {
    agent any 
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
                mvn clean compile 
            }
        }
        
    }
}