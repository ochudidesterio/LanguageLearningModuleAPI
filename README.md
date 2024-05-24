# Learning Language Module API
This Spring Boot project provides an API for managing language learning modules, including user data, lessons, exercises, and user progress tracking.

## Set up instructions
### Prerequisites
- Java 17 or later
- Maven 3.6.3 or later
- MySQL 5.7 or later

## Clone Repository
```git clone <repository_url>```
```cd learning-language-module-api```
## MySQL Configuration
1. ### Creat MySQL database
   ```CREATE DATABASE zeraki;```
2. ### Configure the Database in Spring Boot:
   Update the src/main/resources/application.properties or yaml file with your MySQL database configuration:
   ```
      spring.datasource.url=jdbc:mysql://localhost:3306/zeraki
      spring.datasource.username=your_mysql_username
      spring.datasource.password=your_mysql_password
      spring.jpa.hibernate.ddl-auto=update
      spring.jpa.show-sql=true
      spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
      server.port=8080
   ```
## Build Project
  ```mvn clean package```
## Run the application
  ```java -jar target/learning-language-module-api.jar```

## Access the API documentation
 - Swagger: ```http://localhost:8080/swagger-ui.html```
## API endpoints
   The API provides endpoints for managing user data, lessons, exercises, and user progress tracking. Some key endpoints include:
  - /user: CRUD operations for managing users.
  - /lesson: CRUD operations for managing lessons.
  - /exercise: CRUD operations for managing exercises.
  - /progress: Endpoints for tracking user progress
  - /userlesson: Endpoints to enroll a user to a lesson

## Testing 
   The project includes both unit tests and integration tests to ensure the correctness and reliability of the application. 
   ### Note
   Use H2 database configurations to run your tests in the terminal:
   ```mvn test```
   

