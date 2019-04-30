# acronym-web-app
This branch launches 3x containers: one for MongoDB,
one for nginx/front end, and one for the SpringBoot
API.  After completing these instructions, usage is:

- For Front-end, `localhost:8081`
- For API call, `localhost:8080/acronyms?bullets=<text with acronyms>`

1. Install docker (`docker.io`) and add your user to the 
`docker` group to add `sudo` rights (see https://docs.docker.com/install/linux/linux-postinstall/).

2. Delete any containers named `acronym-nginx-container`,
`acronym-spring-app`, or `acronym-mongo-db`.

3. `git clone` this branch

4. Move into folder with Mongo Dockerfile
```
cd ./acronym-web-app/MongoDocker
```

5. Create docker software-defined network (SDN)
```dockerfile
docker network create acronym-network
```

6. Build MongoDB Docker image named `acronym-mongo-image`
```dockerfile
docker build -t acronym-mongo-image ./
```

7. Run MongoDB Docker image as a daemon, remove on exit, named
`acronym-mongo-db`, map port 27017 on host to 27017 on container,
and add to the SDN we defined.
```dockerfile
docker run -d --rm --name acronym-mongo-db --p 27017:27017 --network acronym-network acronym-mongo-image
```

8. Move to springBoot directory `cd ..`

9. Build Spring Boot image & run
```dockerfile
docker build -t acronym-spring-image
docker run -d --rm --name acronym-spring-app -p 8080:8080 --network acronym-network acronym-spring-image
```

10. Build nginx image & run (uses port 8081 on localhost for nginx http port 80)
```dockerfile
docker build -t acronym-nginx-image
docker run -d --rm --name acronym-nginx-container -p 8081:80 --network acronym-network acronym-nginx-image
```

11. Access the app at `localhost:8081`!
