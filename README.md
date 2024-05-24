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
 - Swagger: ```http://localhost:8080/swagger-ui/index.html```
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

## API Testing step by step

### Basic Auth User
Name: ```testuser```
Password: ```password```

1. Create users 
   POST request to ```http://localhost/user/register```
   ```
   {
   "firstname":"Baraka",
   "lastname":"Osman",
   "password":"12345",
   "username":"osman"
   }
   ```
   
2. ### Create a lesson 
   POST request to ```http://localhost/lesson/create```
   ```
   {
   "title":"History 100"
   }
   ``` 
     
3. ### Create an exercise
   POST request to ```http://localhost/exercise/create```
   ```
   {
   "description":"People and Population",
   "score":30
   }
   ```
   
4. ### Add an exercise to a lesson
   POST request to ```http://localhost/exercise/add-lesson?lessonId=1&exerciseId=1```
   ```@Params```
   ```lessonId``` and ```exerciseId```

5. ### Create user score for a particular lesson on a given exercise
   POST request to ```http://localhost/progress/save?userScore=29&userId=1&exerciseId=1&lessonId=1```
   ```@Params```
   ```userScore``` , ```userId```, ```exerciseId``` and ```lessonId```
   
6. ### To get user progress per lesson
   GET request to ```http://localhost/progress/get?lessonId=1&userId=1```
   ```@Params```
   ```lessonId``` and ```userId```

7. ### To check if user has completed a lesson
   GET request to ```http://localhost/progress/lesson-status?lessonId=1&userId=1```
   ```@Params```
   ```userId``` and ```lessonId```

8. ### To recommend user to next lesson 
   GET request to ```http://localhost/progress/recommendation?lessonId=1&userId=1```
   ```@Params```
   ```lessonId``` and ```userId```
