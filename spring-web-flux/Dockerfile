FROM openjdk:8

ADD target/*.jar /opt/app.jar

EXPOSE 8081
ENTRYPOINT ["java", "-jar", "/opt/app.jar"]
