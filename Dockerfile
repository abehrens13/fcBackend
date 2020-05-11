FROM openjdk:8-jre-alpine
COPY target/*.jar /usr/app/
EXPOSE 8080
CMD ["/usr/bin/java", "-jar", "-Dspring.profiles.active=default", "/usr/app/scrumtestbackend-0.0.1-SNAPSHOT.jar"]
