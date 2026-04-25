# ShopWave 

name: Melat Woldegiorgis

id: ATE/7037/14


## Overview

This is the ShopWave starter application (Spring Boot). It exposes a small product catalog REST API for listing, creating, searching, and updating product stock.

The app uses an in-memory H2 database by default (for development/testing) and the server listens on port 8080 (see `src/main/resources/application.properties`).


## Build

From the project root build the project with Maven:

```bash
mvn clean package
```


## Run


```bash
mvn spring-boot:run
```


The application listens on http://localhost:8080 by default.


## Run tests

Execute the test suite with:

```bash
mvn test
```

