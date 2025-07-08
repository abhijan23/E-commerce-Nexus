# E-Commerce Nexus
## Prerequisites
	👉 Java JDK 17 (https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
	👉 Apache Maven (https://maven.apache.org/download.cgi)
	👉 MySQL Server (https://www.mysql.com/downloads/)
	👉 IDE: IntelliJ IDEA (https://www.jetbrains.com/idea/download/?section=windows)
	👉 Postman(https://www.postman.com/downloads/)
## Development Walkthrough for this project:
For the sake of understanding I'm dividing the development into several phases.
### Phase 1: Foundation & Core Backend API:
#### Step 1: Initialize Your Spring Boot Project:
Open browser and go to [Link](https://start.spring.io/) <br>
Fill in the Project Metadata on the left side of the page as follows:
```
Project: Maven
Language: Java
Spring Boot: Choose a recent stable version (e.g., 3.3.1). Any version like 3.x.x is fine.
Group: com.nexus
Artifact: ecommerce
Name: ecommerce
Description: E-commerce Nexus Project
Package name: com.nexus.ecommerce
Packaging: Jar
Java: 17
Add Dependenceies:
i> Spring Web: Needed to build RESTful web applications. It includes the Apache Tomcat server by default.
ii> Spring Data JPA: To persist data in SQL stores with Java Persistence API using Spring Data and Hibernate.
iii> MySQL Driver: The specific JDBC driver required to connect to a MySQL database.
iv> Lombok: A fantastic library that reduces boilerplate code like getters, setters, and constructors by using annotations.
v> Spring Boot DevTools: Provides fast application restarts, and other development-time features.
Click on Generate and unzip it to the local folder.
```
#### Step 2: Configure the MySQL Database Connection:
Navigate to the src/main/resources folder in your project. <br>
Open the application.properties file and add following configuration code:
```
spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_db?createDatabaseIfNotExist=true
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```