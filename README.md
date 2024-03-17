# **Chatop Rental Backend**

This project is a Spring Boot application designed to manage rental properties. It provides functionalities such as user authentication, listing rentals, and messaging between users.

## **Prerequisites**

Before you begin, ensure you have met the following requirements:

- JDK 17
- Apache Maven
- MySQL Database
- Docker

## **Setting Up the Development Environment**

1. **Clone the repository** to your local machine using:

```bash
git clone
```

1. **Navigate to the project directory**:

```bash
cd rental
```

1. **Configure application properties**:

Edit the **`src/main/resources/application.properties`** file to set your database connection and other environment-specific settings.

1. **Run the application**:

You can start the application using Maven wrapper scripts included in the project.

- For Linux/macOS:

```bash
bashCopy code
./mvnw spring-boot:run
```

- For Windows:

```
Just delete Windows and download a real OS.
```

## **Building and Running with Docker**

If you prefer to containerize your application, follow these steps:

1. **Build the project**:

```bash
./mvnw clean package
```

1. **Build the Docker image**:

```bash
docker build -t chatop-rental-backend .
```

1. **Run the Docker container**:

Make sure to adjust the environment variables according to your setup.

```bash
docker run -d -p 8080:8080 --name chatop-rental chatop-rental-backend
```

Alternatively, use Docker Compose:

```bash
docker-compose up -d
```

## **Accessing the Application**

Once the application is running, you can access the following endpoints:

- **Swagger UI for API documentation**: http://localhost:8080/swagger-ui.html
- **API Base URL**: http://localhost:8080/api/

## **Contributing to the Project**

Why would you do that?
