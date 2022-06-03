# fabrick-onboarding

A Spring Boot application implementing the Test_S_v5.docx requirements.

## Requirements

For building and running the application you need:

- [JDK 11](https://www.oracle.com/it/java/technologies/javase/jdk11-archive-downloads.html)
- [Maven 3](https://maven.apache.org)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `it.aitho.fabrickonboarding.FabrickOnboardingApplication` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
mvn spring-boot:run
```

## Swagger
Swagger is enabled, use it from http://localhost:8080/swagger-ui/index.html

## Database and persistence
The database chosen is an H2 in memory only, so all data will be lost after an application restart.