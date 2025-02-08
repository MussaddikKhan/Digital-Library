# Digital Library Project 📚

Welcome to the Library Management Project! This project is designed to manage library operations efficiently, including managing books, students, and transactions. Below, you will find all the necessary information to get started with this project.

## Table of Contents
- [Features](#features)
- [Getting Started](#getting-started)
- [Installation](#installation)
- [API Documentation](#api-documentation)
- [Contributing](#contributing)
- [Contact](#contact)

## Features

## Features

### Entities

1. **Book**
    - **Create Book**: Adds a new book and returns `bookResponse`.
    - **Find Book**: Supports various filters for searching:
        - By name
        - By name and author
        - By name, author, and language
        - By name and genre
        - By name, author, genre, and language

2. **Author**
    - **Parent Entity**: Authors must be added before books, ensuring every book has an associated author.

3. **Student**
    - **Create Student**: Adds a new student and returns `studentResponse`.
    - **Find Student**: Supports various filters for searching:
        - By roll number (returns `studentResponse`)
        - List of books borrowed by the student
        - List of transactions for the student based on roll number
    - **Update Student**: Allows updating student information (name, age, phone number).

4. **Transaction**
    - **Create Transaction**: Adds a new transaction and returns the transaction ID.
    - **Find Transaction**: Supports various filters for searching:
        - By transaction ID
        - By transaction status (Success/Failed/Pending)
        - By transaction type (Issue/Return)
    - **Additional Features**:
        - Find books by transaction status (Issue/Return)
        - Identify students with overdue books

### Pending Work
- **Security**: Adding authentication and authorization to secure the application. **(Done ✅)**
- **Unit Testing**: Implementing unit tests to ensure the reliability of the application.(One Service Done)

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java 17
- Spring Boot 3.1.4
- Maven 3.6.3
- MySQL
- Postman (optional)

## Dependencies

This project uses the following dependencies:

- Spring Boot Starter Data JPA
- Spring Boot Starter Validation
- Spring Boot Starter Web
- MySQL Connector Java
- Project Lombok (optional)
- Spring Boot Starter Test (for testing)

### Installation

1. **Clone the repository:**
   ```bash
   git clone https://github.com/MussaddikKhan/Digital-Library.git
   cd library-management
   ```
 2. **Configure the `application.properties`:** 
 Update the following properties in the `application.properties` file according to your database settings:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/your_db_name
    spring.datasource.username=your_username
    spring.datasource.password=your_password
    ``` 

### API Documentation

For detailed API documentation, please refer to the Postman collection:

[Digital Library Project API Documentation](https://documenter.getpostman.com/view/29782116/2sA3e1Bq4T)

### Contact

For any inquiries or feedback, please contact us at [khanmusaddik122@gmail.com](mailto:khanmusaddik122@gmail.com).



   

