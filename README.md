# CODING CHALLENGE 
##API FOR SUBSCRIPTIONS
###### WEB SERVICES AND INTEGRATION

## Usage

With [docker](https://www.docker.com/) installed, go to root folder where `docker-compose.yml` file is located and run:

    $ docker up --build

Then do to [swagger-ui](http://localhost:8080/swagger-ui/#/subscription-controller/subscribeUsingPOST) and try to execute subscription endpoint.

Also, you can run each microservice in docker container independently from root folder of each service where `Dockerfile` file is located by running:

    $ docker build -t tagname .
    $ docker run tagname
    
## Build
Each service can be build from root folder by running:

    $ mvn clean install

## Technologies and usage
- **Spring Boot** for IOC container and auto configuration spring starter projects;
- **Spring Web** for building RESTful API and Embedded Tomcat container;
- **Spring WebFlux** for nonblocking reactive api calls and functional error handling;
- **Spring Data JPA** for working with database;
- **Spring AMQP** for integrating with rabbitMQ;
- **Spring Validation** for request validation;
- **Lombok** to avoid repetitive code;
- **Log4j** for event logging;
- **Swagger** for API documentation;
- **JUnit and Mockito** for testing and mocking purposes;
- **Model Mapper** for mapping DTO and entities;
- **PostgreSQL** for storing data;
- **Maven** for dependency management;
- **Docker** for running each service independently from each other.
