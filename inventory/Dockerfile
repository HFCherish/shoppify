FROM openjdk:8-jdk-alpine
VOLUME /tmp
ADD target/*.jar /var/run/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/var/run/app.jar"]