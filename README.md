# fcBackend - FlashCard Backend

Sample Projekt.

## docker build
 - docker build -t feb18/fcBackend:latest .
 - docker build -t feb18/fcBackend:latest-dev .
 - docker run --name scrumtestbackend-dev -p 8080:8080 feb18/fcBackend:latest-dev

## docker-compose build
 - docker-compose up --build
 - docker-compose up -d
 - docker-compose down


## maven build
mvn clean install
mvn docker:build
mvn docker:start
mvn docker:stop


## Install and Usage of Sonarqube

 - Sonarqube has been configured for Port 9000, localhost and usage of the a postgres database sonar. Do the following steps for setup:
 - docker-compose -f docker-compose-dev.yml up
 - after the Sonarqube container stops create a database "sonar" on postgres with user postgres
 - docker-compose -f docker-compose-dev.yml down
 - docker-compose -f docker-compose-dev.yml up
 - wait some time till sonarqube has created the database tabels
 
 after successful installation
 - mvn clean verify sonar:sonar
 - or wait till jenkins made the sonar stuff
 
## merge to master branch
 - git branch - show branches
 - git branch new-branch - creates new-branch
 - git checkout new-branch
 - git ... - do some stuff on new-branch 
 - git checkout master - switch to master branch
 - git merge new-branch - merge new-branch into master 

 Additional you have to update the following files
 - pom.xml (version)
 - jenkinsfile (version)
 - Dockerfile (version)

## TODO
