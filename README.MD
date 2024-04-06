# Spring Boot Multi-Module Application

This is a Spring Boot multi-module application that consists of multiple modules for different functionalities. The project structure includes modules for job1, job2, and utils, with the root module serving as the parent.

## Modules

### Root Module
- Parent module that defines common dependencies and configurations for all other modules.
- Contains the project's parent POM file (`pom.xml`).

### Job1 Module
- Spring Batch application module responsible for reading orders from the orders.csv file and inserting them into the database.
- Inherits dependencies and configurations from the root module.
- Uses utility methods from the utils module.

### Job2 Module
- Spring Batch application module responsible for reading users from the users.csv file and inserting them into the database.
- Inherits dependencies and configurations from the root module.
- Uses utility methods from the utils module.

### Utils Module
- Common module containing utility methods and functionalities that can be shared between job1 and job2 modules.
- Provides reusable components and helper methods.

## Usage
1. Clone the repository: git clone https://github.com/HoangVanBinh0712/spring_batch.git

2. Navigate to the root directory of the project:cd spring-batch

3. Create a schema named "batch" then run the script.sql

4. Modify the database username and password in application.properties files of job1 and job2 to your database username and password

5. Build the project using Maven: mvn clean install

6. Run the desired module: go to job1/target/ then enter the command (java -jar job_read_order_csv-0.0.1-SNAPSHOT.jar) this will run the batch.
