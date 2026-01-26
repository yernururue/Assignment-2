Library Management System (JDBC + OOP)

Project Overview

This project is a Library Management System implemented in Java using JDBC and object-oriented design principles.
It demonstrates a multi-layer architecture (model → service → repository/DAO → main controller) with a real PostgreSQL database.

The system supports managing authors and books (printed and electronic), including CRUD operations, validation, exception handling, and polymorphism.



OOP Design

Abstract Class & Inheritance
		Book (abstract)
		Fields: id, title, author, year, isbn, book_type
		Abstract methods:
		calculateLateFee(int days)
		getAccessInstructions()
		Concrete method:
		displayInfo()

Subclasses:
		EBook
		printedBook

Both override abstract methods and implement specific behavior.



Interfaces
		Borrowable
		borrow(), returnItem(), isAvailable()
		DigitalAccess
		getDownloadURL(), getFileSize()

Implemented by:
		EBook → Borrowable, DigitalAccess
		printedBook → Borrowable



Polymorphism

Books are handled using the base Book reference:

Book book1 = new EBook(...);
Book book2 = new printedBook(...);

Calling calculateLateFee() and getAccessInstructions() produces different behavior depending on the runtime type.



Composition
	•	Book HAS-A Author
	•	A Book cannot exist without an Author
	•	Demonstrated via Book.getAuthor() in runtime



Validation & Business Logic

Validation is enforced at multiple layers:
		Model layer: setters validate empty strings, negative values, invalid years
		Service layer:
		Author must exist
		ISBN must be unique
		Business rules enforced before DB operations


JDBC & Database Layer

Database
		PostgreSQL
		Two related tables: authors, books
		Foreign key: books.author_id → authors.id
		Unique constraint: isbn

JDBC Rules Followed
		DriverManager used
		PreparedStatement only (no Statement)
		Proper handling of Connection, ResultSet, and exceptions



CRUD Operations

Implemented via DAO + Service layers:
		create
		getAll
		getById
		update
		delete

All CRUD operations are demonstrated in Main.java.


Exception Handling

Custom Exception Hierarchy
		InvalidInputException
		DuplicateResourceException (extends InvalidInputException)
		ResourceNotFoundException
		DatabaseOperationException

Exceptions are triggered and demonstrated during runtime.



Running the Project

Requirements
		Java 17+
		PostgreSQL
		JDBC Driver

Steps
	1.	Create database and run schema.sql
	2.	Update DB credentials in DatabaseConnection
	3.	Compile and run:

javac Main.java
java Main




Project Structure

src/
├── model/
├── service/
├── repository/
├── exception/
├── utils/
├── resources/schema.sql
└── Main.java



Demonstration

The Main class demonstrates:
		Polymorphism
		Interface usage
		CRUD operations
		Validation errors
		Custom exception handling
		Composition

Screenshots are located in:

docs/screenshots/



Reflection

This project strengthened understanding of:
	Abstract classes and polymorphism
	JDBC with PreparedStatements
	Layered architecture
	Exception-driven design
	Clean separation of concerns
