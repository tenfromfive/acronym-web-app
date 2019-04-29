# acronym-web-app

1. Build Mongo DB Container
```
cd MongoDocker
docker build -t mongo-acronym-db .
```

2. Create Docker Network to Support Future linking across different container services
```
docker network create acronym-network
```

3. Start Mongo DB container
```
docker run -d --name acronymmongodb -p 27017:27017 --network acronym-network mongo-acronym-db
```
- Verify by connecting to the container and running queries to ensure initialization was successful
```
docker exec -it acronymmongodb bash
mongo
show dbs
use start
db.acronoyms.find()
```

4. Verify Spring Boot App and connect to mongo db instance running as container by using the default/dev profile
```
mvn spring-boot:run
```
- This will utilize the properties defined in the application.properties file
- http://localhost:8080/acronyms?bullets=

5. Ensure that the host for the mongo db in the application-prod.properties file aligns with the name of the mongo db service running
- If you utilize the naming conventions here they will align

6. Build Spring Boot App Container (assuming unit tests are failing)
```
mvn compile package -Dmaven.test.skip=true
docker build -t spring-boot-app .
```
- Notice that the Dockerfile specifies the prod profile when running the application. This will pull in properties from application-prod.properties

7. Run Spring Boot App Container
```
docker run -d --name spring-boot-app -p 8080:8080 --network acronym-network spring-boot-app
```
- http://localhost:8080/acronyms?bullets=
