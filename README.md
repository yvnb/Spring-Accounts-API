# Spring-Accounts-API
This is Spring Boot APP for fetching the Account Statements through Rest API

# Project Build & Run using Maven Command
Go to the Project directory and run the following command for building and packaging the application

mvn clean install

Once the build is successful, we can run the application either by mvn or java command

mvn spring-boot:run

java -jar path/to/your/jarfile.jar com.nagarro.auth.SpringAccountsAPI

# API Check with Swagger UI
To Check the API through Swagger UI within the application, please navigate to http://localhost:8080/swagger-ui.html

In case of testing from Postman, please use the below url with basic auth selected

http://localhost:8080/api/account/statement?id=1&fromDate=01012020&toDate=01042021&frmAmt=100&toAmt=600

valid users - admin/admin -> for all parameters, user/user -> for only id parameter


