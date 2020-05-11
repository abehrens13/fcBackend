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


## TODO
