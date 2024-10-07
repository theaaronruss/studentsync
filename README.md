# StudentSync

StudentSync is a student management system built using:
- Java
- Spring Boot
- Swagger UI
- MySQL

## Running the Project

***Note:*** In order to run this project, you will need to install [Java](https://www.oracle.com/java/technologies/downloads/)
(Java 21 minimum) and [MySQL](https://www.mysql.com/downloads/).
To run this project, follow the steps below:
1. Clone the repository to your local machine
2. With MySQL installed, create a new schema named `studentsync` and a new user with full privileges on that schema
3. Run the `create_db.sql` script in the `sql_scripts` directory to create the necessary tables
4. Update the `application.properties` file in the `src/main/resources` directory with the correct database connection
details
   ```yaml
   spring:
     datasource:
       url: jdbc:mysql://localhost:3306/studentsync
       username: ssuser
       password: password
   ```
5. Open command prompt or terminal (depending on your OS) and navigate to the project directory
6. Run the following commands to build and start the application:
   ```bash
   gradlew build
   gradlew bootRun
   ```
7. Open a web browser and navigate to [http://localhost:8080/swagger-ui](http://localhost:8080/swagger-ui) to access the
REST API endpoints

## Creating a user

To create a user in StudentSync, you will need to manually enter a username and password into the `user` table in the
database. Add the desired username (limit of 50 characters) into the `username` column, and the password into the
`password` column. If the password is not hashed, prefix the password in the database with `{noop}`. If the password is
hashed using Bcrypt, prefix it with `{bcrypt}`. Some examples are below:

| username | password              |
|----------|-----------------------|
| user1    | {noop}password        |
| user2    | {bcrypt}$2a$10$1Q7... |
