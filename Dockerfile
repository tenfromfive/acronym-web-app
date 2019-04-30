FROM openjdk:8
COPY ./target/acronym-web-app-0.0.1-SNAPSHOT.jar /usr/bin
WORKDIR /usr/bin
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "acronym-web-app-0.0.1-SNAPSHOT.jar"]
