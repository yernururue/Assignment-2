# Library Management System

Spring Boot REST API with Design Patterns

## Quick Start

```bash
mvn spring-boot:run
```

**Access:**
- API: http://localhost:8080/api/books
- H2 Console: http://localhost:8080/h2-console (JDBC URL: `jdbc:h2:mem:librarydb`, User: `sa`, Password: empty)

## API Endpoints

### Books
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/books` | Get all books |
| GET | `/api/books/{id}` | Get book by ID |
| POST | `/api/books` | Create book |
| PUT | `/api/books/{id}` | Update book |
| DELETE | `/api/books/{id}` | Delete book |
| GET | `/api/books/search?keyword=` | Search by title |
| GET | `/api/books/available` | Get available books |

### Borrowings
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/borrowings` | Get all borrowings |
| GET | `/api/borrowings/{id}` | Get borrowing by ID |
| POST | `/api/borrowings` | Create borrowing |
| PUT | `/api/borrowings/{id}/return` | Return book |
| GET | `/api/borrowings/member/{name}` | By member |
| GET | `/api/borrowings/active` | Active borrowings |

## Design Patterns

### Singleton Pattern - `DatabaseConfig.java`
Ensures single database configuration instance with thread-safe lazy initialization.

### Factory Pattern - `BookFactory.java`
Creates `PhysicalBook` or `EBook` instances based on type parameter.

### Builder Pattern - `BorrowingBuilder.java`
Fluent interface for constructing `Borrowing` objects step-by-step.

## Project Structure

```
src/main/java/com/library/
├── LibraryApplication.java
├── controller/
│   ├── BookController.java
│   └── BorrowingController.java
├── service/
│   ├── BookService.java
│   └── BorrowingService.java
├── repository/
│   ├── BookRepository.java
│   ├── AuthorRepository.java
│   └── BorrowingRepository.java
├── model/
│   ├── Book.java (abstract)
│   ├── PhysicalBook.java
│   ├── EBook.java
│   ├── Author.java
│   └── Borrowing.java
├── dto/
│   ├── BookDTO.java
│   └── BorrowingDTO.java
├── patterns/
│   ├── DatabaseConfig.java (Singleton)
│   ├── BookFactory.java (Factory)
│   └── BorrowingBuilder.java (Builder)
└── exception/
    ├── GlobalExceptionHandler.java
    ├── ResourceNotFoundException.java
    └── ErrorResponse.java
```

## Technologies

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- H2 Database
- Maven
