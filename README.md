# Marketplace Backend Project

This project is designed to create a backend system for a marketplace where users can buy and sell products. It includes user authentication, authorization, and CRUD (Create, Read, Update, Delete) endpoints for managing users, products, and categories. Below, you'll find essential information about the project structure, technologies used, and how to set it up.


---

## Technologies Used

- **Java 20:** The primary programming language for the project.
- **Spring Framework (3+):** Utilized for building the application's architecture and handling various aspects like security, dependency injection, and more.
- **Spring Security:** Used for authentication and authorization of users.
- **Hibernate:** An Object-Relational Mapping (ORM) tool to interact with the PostgreSQL database.
- **PostgreSQL:** The chosen database for storing user data, products, and categories.
- **Postman:** Recommended for testing API endpoints during development.
- **Git/GitHub:** Version control and collaboration platform for managing the project's source code.
- **Lombok:** Simplifies code writing by reducing boilerplate code for DTOs and entities.


---

## Authentication and Authorization
Authentication is implemented using JSON Web Tokens (JWT). When a user successfully logs in, a JWT token is generated and returned in the response.
The application has two roles: USER and ADMIN. Users with the ADMIN role have access to additional administrative endpoints.
Spring Security is configured to enforce authentication and authorization for each endpoint. Users must include their JWT token in the Authorization header to access protected resources.
