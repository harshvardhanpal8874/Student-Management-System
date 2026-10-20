# Student Management System

A backend-focused Student Management System built using **Java, Spring Boot, Spring Security, Spring Data JPA, and PostgreSQL**, with a frontend built using HTML, CSS, and JavaScript.

The project demonstrates REST API development, database relationships, validation, exception handling, authentication, and role-based authorization.

## Features

- Student CRUD operations (Create, Read, Update, Delete)
- Department management
- Search students by name
- DTO-based request and response handling
- Input validation using Bean Validation
- Global exception handling
- PostgreSQL database integration with Spring Data JPA
- One-to-Many and Many-to-One relationships between departments and students
- User registration with BCrypt password encoding
- Role-based authorization for users and administrators
- Spring Security and HTTP Basic authentication
- Request logging and authentication filters
- Simple frontend using HTML, CSS, and JavaScript

## Tech Stack

| Technology | Purpose |
|---|---|
| Java | Programming language |
| Spring Boot | Backend application |
| Spring Security | Authentication and authorization |
| Spring Data JPA | Database operations |
| Hibernate | ORM |
| PostgreSQL | Relational database |
| Maven | Dependency management |
| HTML, CSS, JavaScript | Frontend |
| Postman | API testing |

## Project Structure

```text
student-management-complete/
├── src/
│   └── main/
│       ├── java/com/example/studentManagement/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── exception/
│       │   ├── filter/
│       │   ├── repository/
│       │   ├── security/
│       │   └── service/
│       └── resources/
│           ├── static/
│           │   ├── index.html
│           │   ├── script.js
│           │   └── style.css
│           └── application.properties
├── .gitignore
└── pom.xml
```
## Author

**Harsh Vardhan Pal**

- GitHub: [@harshvardhanpal8874](https://github.com/harshvardhanpal8874)
- Project: [Student Management System](https://github.com/harshvardhanpal8874/Student-Management-System)



## Database Configuration

1. Install PostgreSQL and create a database named `Studentdb`, or use the database name configured in your application.
2. Configure the database URL and username in `application.properties`.
3. Set the `DB_PASSWORD` environment variable
