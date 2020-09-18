# fcBackend - FlashCard Backend

## pre
extend /etc/hosts with 
127.0.0.1 mongo
127.0.0.1 redis


FlashCard game for AWS

## docker build
 - docker build -t feb18/fcbackend:latest .
 - docker build -t feb18/fcbackend:latest-dev .
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
