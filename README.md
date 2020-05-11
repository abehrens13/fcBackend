# ipsc

Sample Projekt.

## docker build
 - docker build -t feb18/scrumtestbackend:latest .
 - docker build -t feb18/scrumtestbackend:latest-dev .
 - docker run --name scrumtestbackend-dev -p 8080:8080 feb18/scrumtestbackend:latest-dev

## docker-compose build
 - docker-compose up --build
 - docker-compose up -d 
 - docker-compose down 


## maven build
mvn clean install
mvn docker:build
mvn docker:start
mvn docker:stop


## TODO
  - unit tests for travis-ci
  - initial database setup with meaningful test data
  - email setup with email templates
  - mechanism for login and safety
  - deployment for AWS
