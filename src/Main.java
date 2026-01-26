import exception.*;
import model.*;
import service.*;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();

        System.out.println("LIBRARY MANAGEMENT SYSTEM");

        try {
            //Polymorphism
            System.out.println("1. POLYMORPHISM\n");

            Author author = new Author(25, "George Orwell", 1960, "British");

            // Create using BASE CLASS reference - this is polymorphism!
            Book book1 = new EBook(24, "1984", author, 1994, "12345678910112", 3.4, "example.com");
            Book book2 = new printedBook(25, "Animal Farm", author, 1990, "9876543211090", "A1-B3", 0.450);

            System.out.println("Created books using Book reference:");
            System.out.println("  Book 1: " + book1.getClass().getSimpleName() + " - " + book1.getTitle());
            System.out.println("  Book 2: " + book2.getClass().getSimpleName() + " - " + book2.getTitle());

            // Polymorphic method calls - SAME method, DIFFERENT behavior
            System.out.println("\nLate fees for 10 days:");
            System.out.println("  EBook late fee: " + book1.calculateLateFee(10));
            System.out.println("  Printed late fee: " + book2.calculateLateFee(10));

            System.out.println("\nAccess instructions:");
            System.out.println("  EBook: " + book1.getAccessInstructions());
            System.out.println("  Printed: " + book2.getAccessInstructions());

            // Polymorphic collection
            System.out.println("\nProcessing books in array:\n");
            Book[] library = {book1, book2};

            for (Book b : library) {
                System.out.println(b.getTitle());
            }


            // 2. Interfaces
            System.out.println("2.INTERFACES\n");

            EBook ebook = new EBook(1, "Harry Potter", author, 1997, "9780747532699", 2.5, "example2.com");
            printedBook pbook = new printedBook(2, "The Hobbit", author, 1937, "9780547928227", "C3-D4", 0.6);

            // Borrowable interface
            System.out.println("Borrowable Interface (EBook):");
            if (ebook instanceof Borrowable) {
                Borrowable b = (Borrowable) ebook;
                System.out.println("Initial availability = " + b.isAvailable());
                b.borrow();
                System.out.println("After borrow: availability = " + b.isAvailable());
                b.returnItem();
                System.out.println("  After return: availability = " + b.isAvailable());
            }

            // DigitalAccess interface
            System.out.println("\nDigitalAccess Interface (EBook only):");
            if (ebook instanceof DigitalAccess) {
                DigitalAccess d = (DigitalAccess) ebook;
                System.out.println("Download url: " + d.getDownloadURL());
                System.out.println("File Size: " + d.getFileSize() + " MB");
            }


            // 3. CRUD OPERATIONS
            System.out.println("3. CRUD OPERATIONS\n");

            // CREATE
            System.out.println("--- CREATE ---");
            Author einstein = new Author(0, "Einstein", 1899, "American");
            authorService.createAuthor(einstein);
            System.out.println("Author created.");

            List<Author> authors = authorService.getAllAuthors();
            Author author1 = authors.get(0);

            EBook newEbook = new EBook(0, "Old Man and Sea", author1, 1952, "9780684801223", 1.5, "books234.com");
            bookService.creteEbook(newEbook);
            System.out.println("Ebook created: Old man and Sea\n");

            printedBook newPbook = new printedBook(0, "Pride and Prejudice", author1, 1813, "9780141439518", "E5-F6", 0.4);
            bookService.createPrintedBook(newPbook);
            System.out.println("Printed Book created: Pride and Prejudice\n");

            // READ
            System.out.println("--- READ ---");
            List<Book> allBooks = bookService.getAllBooks();
            System.out.println("Total books: " + allBooks.size());
            System.out.println("Books in database:");
            for (Book b : allBooks) {
                System.out.println("ID: " + b.getId() + " " + b.getTitle() +
                        " (" + b.getYear() + ") by " + b.getAuthor().getName());
            }

            if (!allBooks.isEmpty()) {
                System.out.println("\nGetting specific book by ID:");

                try {
                    Book specificBook = bookService.getBookByID(23);
                    System.out.println("Retrieved: " + specificBook.getTitle());
                    specificBook.displayInfo();
                } catch (ResourceNotFoundException e) {
                    System.out.println("This book does not exist");
                }
            }

            // UPDATE
            System.out.println("\n--- UPDATE ---");
            if (!allBooks.isEmpty()) {
                try {
                    Book bookToUpdate = bookService.getBookByID(10);
                    String oldTitle = bookToUpdate.getTitle();
                    System.out.println("Original title: " + oldTitle);

                    bookToUpdate.setTitle("New Title");
                    System.out.println("New title: " + bookToUpdate.getTitle());

                } catch (ResourceNotFoundException e) {
                    System.out.println("This book does not exist");
                }

            }

            // DELETE
            System.out.println("\n--- DELETE ---");
            int countBefore = bookService.getAllBooks().size();
            System.out.println("Books before delete: " + countBefore);

            if (!allBooks.isEmpty()) {
                int idToDelete = allBooks.get(allBooks.size() - 1).getId();
                String titleToDelete = allBooks.get(allBooks.size() - 1).getTitle();
                bookService.deleteBookID(idToDelete);
                System.out.println("✓ Deleted: " + titleToDelete + " (ID: " + idToDelete + ")");

                int countAfter = bookService.getAllBooks().size();
                System.out.println("Books after delete: " + countAfter);
            }
            System.out.println("CRUD operations completed\n");


            // 4. EXCEPTION HANDLING
            System.out.println("4. EXCEPTION HANDLING\n");

            Author testAuthor = new Author(1, "Test Author", 1980, "American");

            // Test InvalidInputException
            System.out.println("Test 1: InvalidInputException (Empty Title)");
            try {
                EBook invalidBook = new EBook(0, "", testAuthor, 2020, "9781111111111", 2.0, "url");
                bookService.creteEbook(invalidBook);
            } catch (InvalidInputException e) {
                System.out.println("Exception: " + e.getClass().getSimpleName());
                System.out.println("Message: " + e.getMessage());
            }

            // Test ResourceNotFoundException
            System.out.println("\nTest 2: ResourceNotFoundException (Not existing Book)");
            try {
                bookService.getBookByID(99999);
            } catch (ResourceNotFoundException e) {
                System.out.println("Caught: " + e.getClass().getSimpleName());
                System.out.println("Message: " + e.getMessage());
            }

            // Test InvalidInputException - Negative file size
            System.out.println("\nTest 3: InvalidInputException (Negative File Size)");
            try {
                EBook invalidBook = new EBook(0, "Valid Title", testAuthor, 2020, "9782222222222", -5.0, "url");
                bookService.creteEbook(invalidBook);
                System.out.println("Should have thrown exception!");
            } catch (InvalidInputException e) {
                System.out.println("Caught: " + e.getClass().getSimpleName());
                System.out.println("Message: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Wrong exception: " + e.getClass().getSimpleName());
            }

            System.out.println("\nException handling demonstrated!\n");

            // 5. VALIDATION DEMONSTRATION
            System.out.println("5. VALIDATION\n");

            // Model Layer Validation (Setters)
            System.out.println("Model Layer Validation (Setters):");
            EBook book3 = new EBook(1, "Test", testAuthor, 2020, "9783333333333", 2.0, "url");

            try {
                book3.setTitle("");
            } catch (IllegalArgumentException e) {
                System.out.println("Setter validation: " + e.getMessage());
            }

            try {
                book3.setYear(3000);
            } catch (IllegalArgumentException e) {
                System.out.println("Setter validation: " + e.getMessage());
            }

            try {
                book3.setFilesize(-10);
            } catch (IllegalArgumentException e) {
                System.out.println("Setter validation: " + e.getMessage());
            }

            // Service Layer Validation
            System.out.println("\nService Layer Validation:");
            System.out.println("  Service checks: author exists, ISBN unique, business rules");
            System.out.println("  Service throws: InvalidInputException, DuplicateResourceException");
            System.out.println("  This was demonstrated in Exception Handling section above");

            System.out.println("\nValidation demonstrated!\n");


            // 6. COMPOSITION DEMONSTRATION
            System.out.println("6. COMPOSITION (Book HAS-A Author)\n");

            if (!allBooks.isEmpty()) {
                Book book = allBooks.get(0);
                Author bookAuthor = book.getAuthor();

                System.out.println("Book: " + book.getTitle());
                System.out.println("Name: " + bookAuthor.getName());
                System.out.println("Birth Year: " + bookAuthor.getBirthYear());
                System.out.println("Nationality: " + bookAuthor.getNationality());
                System.out.println("\nThis demonstrates COMPOSITION:");
                System.out.println("Book has an Author object");
                System.out.println("Cannot create Book without Author");
            }

            System.out.println("\nComposition demonstrated!\n");

        } catch (Exception e) {
            System.err.println("\nError: " + e.getMessage());
            e.printStackTrace();
        }

    }
}