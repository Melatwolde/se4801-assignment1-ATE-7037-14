# ShopWave 

name: Melat Woldegiorgis

id: ATE/7037/14


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


For this assignment, my choice of AI tool was Grok I mainly used it to help me understand the project structure and to break down the assignment into smaller, manageable tasks so I could commit multiple times as the teacher requested.
I used Grok the most during the planning and implementation phases. It helped me visualize the correct folder structure and repository layout. In C2, I got assistance on how to properly design and implement the entity classes, including the correct use of JPA annotations, Lombok annotations (@Data, @Builder, @Entity, @ManyToOne, @OneToMany, orphanRemoval), the convenience method in the Order class, and setting up the relationships between Order and OrderItem.

```bash
mvn test
```

