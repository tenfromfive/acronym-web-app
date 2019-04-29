FROM registry.access.redhat.com/redhat-openjdk-18/openjdk18-openshift
COPY target/acronym-web-app-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","app.jar"]
