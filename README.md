# Task Management API

A RESTful API for managing tasks and task lists, built with Spring Boot. This application allows users to create, organise, and track tasks within customisable lists, supporting features like priority levels, due dates, and status tracking.

## Features

- **Task Management**: Create, read, update, and delete tasks
- **Task Lists**: Organise tasks into lists for better categorisation
- **Priority Levels**: Assign LOW, MEDIUM, or HIGH priority to tasks
- **Status Tracking**: Mark tasks as OPEN or CLOSED
- **Due Dates**: Set and track task deadlines
- **RESTful API**: Full CRUD operations via HTTP endpoints
- **Validation**: Input validation with meaningful error messages
- **Auditing**: Automatic timestamp tracking for created/updated fields

## Technologies Used

- **Java 25**
- **Spring Boot 4.0.1**
- **Spring Data JPA** for data persistence
- **PostgreSQL** as the production database
- **H2 Database** for development and testing
- **Lombok** for reducing boilerplate code
- **Maven** for dependency management
- **Jakarta Validation** for input validation

## Prerequisites

- Java 25 or higher
- Maven 3.6+
- PostgreSQL (for production)
- H2 Database (automatically included for development)

## Installation and Setup

1. **Clone the repository:**
   ```bash
   git clone <repository-url>
   cd tasks
   ```

2. **Configure the database:**
   - For development: H2 database is used automatically
   - For production: Update `src/main/resources/application.properties` with your PostgreSQL credentials:
     ```
     spring.datasource.url=jdbc:postgresql://localhost:5432/tasks
     spring.datasource.username=your-username
     spring.datasource.password=your-password
     ```

3. **Build the application:**
   ```bash
   mvn clean install
   ```

4. **Run the application:**
   ```bash
   mvn spring-boot:run
   ```

The application will start on `http://localhost:8080`.

## API Documentation

### Base URL
```
http://localhost:8080/api/v1
```

### Task Lists Endpoints

#### Get All Task Lists
- **GET** `/task-lists`
- **Response:** Array of TaskListResponse

#### Get Task List by ID
- **GET** `/task-lists/{id}`
- **Parameters:** `id` (UUID) - Task list ID
- **Response:** TaskListResponse

#### Create Task List
- **POST** `/task-lists`
- **Body:** TaskListRequest
- **Response:** TaskListResponse (201 Created)

#### Update Task List
- **PUT** `/task-lists/{id}`
- **Parameters:** `id` (UUID) - Task list ID
- **Body:** TaskListRequest
- **Response:** TaskListResponse

#### Delete Task List
- **DELETE** `/task-lists/{id}`
- **Parameters:** `id` (UUID) - Task list ID
- **Response:** 204 No Content

### Tasks Endpoints

#### Get All Tasks
- **GET** `/tasks`
- **Response:** Array of TaskResponse

#### Get Task by ID
- **GET** `/tasks/{id}`
- **Parameters:** `id` (UUID) - Task ID
- **Response:** TaskResponse

#### Get Tasks by Task List
- **GET** `/tasks/list/{taskListId}`
- **Parameters:** `taskListId` (UUID) - Task list ID
- **Response:** Array of TaskResponse

#### Create Task
- **POST** `/tasks`
- **Body:** TaskRequest
- **Response:** TaskResponse (201 Created)

#### Update Task (Partial)
- **PATCH** `/tasks/{id}`
- **Parameters:** `id` (UUID) - Task ID
- **Body:** TaskPatchRequest
- **Response:** TaskResponse

#### Delete Task
- **DELETE** `/tasks/{id}`
- **Parameters:** `id` (UUID) - Task ID
- **Response:** 204 No Content

### Data Models

#### TaskListRequest
```json
{
  "title": "string (required)",
  "description": "string (optional)"
}
```

#### TaskListResponse
```json
{
  "id": "UUID",
  "title": "string",
  "description": "string",
  "created": "LocalDateTime",
  "updated": "LocalDateTime"
}
```

#### TaskRequest
```json
{
  "title": "string (required)",
  "description": "string (optional)",
  "dueDate": "LocalDateTime (optional)",
  "status": "OPEN|CLOSED (optional, defaults to OPEN)",
  "priority": "LOW|MEDIUM|HIGH (optional, defaults to MEDIUM)",
  "taskListId": "UUID (required)"
}
```

#### TaskResponse
```json
{
  "id": "UUID",
  "title": "string",
  "description": "string",
  "dueDate": "LocalDateTime",
  "status": "OPEN|CLOSED",
  "priority": "LOW|MEDIUM|HIGH",
  "taskListId": "UUID",
  "created": "LocalDateTime",
  "updated": "LocalDateTime"
}
```

#### TaskPatchRequest
```json
{
  "title": "string (optional)",
  "description": "string (optional)",
  "dueDate": "LocalDateTime (optional)",
  "status": "OPEN|CLOSED (optional)",
  "priority": "LOW|MEDIUM|HIGH (optional)",
  "taskListId": "UUID (optional)"
}
```

#### ErrorResponse
```json
{
  "timestamp": "LocalDateTime",
  "status": "int",
  "error": "string",
  "message": "string",
  "path": "string"
}
```

## Database Schema

The application uses JPA entities with the following main tables:

- **task_lists**: Stores task list information
- **tasks**: Stores task information with foreign key to task_lists

Database schema is automatically created/updated using Hibernate (`spring.jpa.hibernate.ddl-auto=update`).

## Error Handling

The API provides comprehensive error handling:

- **400 Bad Request**: Validation errors or invalid request data
- **404 Not Found**: Resource not found
- **405 Method Not Allowed**: Unsupported HTTP method
- **500 Internal Server Error**: Unexpected server errors

All errors return a consistent ErrorResponse format.

## Development

### Running Tests
```bash
mvn test
```

### H2 Console (Development)
When running in development mode, access the H2 console at:
```
http://localhost:8080/h2-console
```
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Username:** `sa`
- **Password:** (leave blank)

### Code Structure
```
src/main/java/com/peter/
├── controller/          # REST controllers
├── domain/
│   ├── dto/            # Data Transfer Objects
│   └── entities/       # JPA entities
├── exception/          # Custom exceptions and global handler
├── mappers/            # Entity-DTO mapping interfaces
│   └── impl/          # Mapping implementations
├── repositories/       # JPA repositories
├── services/           # Business logic interfaces
│   └── imp/           # Service implementations
└── config/            # Configuration classes
```

## Contributing

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests for new functionality
5. Ensure all tests pass
6. Submit a pull request

## Licence

This project is licensed under the MIT Licence.