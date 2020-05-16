pipeline {
  agent { dockerfile true }
  stages {
    stage('Initialize') {
      steps {
        sh '''
                    echo "PATH = ${PATH}"
                    echo "M2_HOME = ${M2_HOME}"
                '''
      }
    }

    stage('Build') {
      steps {
        sh 'mvn -DskipTests clean package' 
      }
    }
    stage('Test') {
      steps {
        sh 'mvn test' 
      }
      post {
        success {
          junit 'target/surefire-reports/**/*.xml' 
        }
      }    
    }
    /**
    https://tutorials.releaseworksacademy.com/learn/building-your-first-docker-image-with-jenkins-2-guide-for-developers
    https://blog.csuttles.io/using-jenkins-and-aws-to-build-and-push-docker-images/
    */
    
     stage('Build image') {
        /* This builds the actual image; synonymous to
         * docker build on the command line */

        app = docker.build("feb18/fcBackend")
    }
    
    stage('Push image') {
        /* Finally, we'll push the image with two tags:
         * First, the incremental build number from Jenkins
         * Second, the 'latest' tag.
         * Pushing multiple tags is cheap, as all the layers are reused. */
        docker.withRegistry('https://registry.hub.docker.com', 'docker-hub-credentials') {
            app.push("0.0.1-SNAPSHOT")
            //app.push("latest")
        }
    }
    
  }
  tools {
    maven '/usr/local/bin/mvn'
    jdk 'JDK8'
  }
}
