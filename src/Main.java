import exception.*;
import model.*;
import service.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BookService bookService = new BookService();
        AuthorService authorService = new AuthorService();
        double lateFeeCalc = 0.0;
        System.out.println("---Library management system---");

        try {
            Author author = new Author(25, "samp", 1960, "enshtein");
            EBook ebook = new EBook(24, "titlens", author, 1994, "123456789101", 3.4, "example.com");
            printedBook printedBook = new printedBook(25, "titless", author, 1990, "110987654321", "A-13", 0.450);
            System.out.println("Late fee: \n");
            lateFeeCalc = ebook.calculateLateFee(10);
            double latefee2 = printedBook.calculateLateFee(6);
            System.out.println("Ebook: "+ lateFeeCalc);
            System.out.println("\nPrinted book: " + latefee2);
            System.out.println("Access for printed book: " + printedBook.getAccessInstructions());
            System.out.println("Access for eBook: " + ebook.getAccessInstructions());

            System.out.println("Interfaces");
            if (ebook instanceof Borrowable) {
                Borrowable b = (Borrowable) ebook;
                System.out.println("Available: "+ b.isAvailable());
                b.borrow();
                System.out.println("After borrow: " + b.isAvailable());
            }
            if (ebook instanceof DigitalAccess) {
                DigitalAccess d = (DigitalAccess) ebook;
                System.out.println("URL: " + d.getDownloadURL());
                System.out.println("Size in MB" + d.getFileSize());
            }

            //CRUD

            System.out.println("CREATE");
            Author einshtein = new Author(33, "Albert", 1940, "Evrei");
            authorService.createAuthor(einshtein);
            System.out.println("Author created");
            authorService.getAuthorByID(6);
            List<Author> authors = authorService.getAllAuthors();

            for (Author a : authors) {
                System.out.println(a.getId());
                System.out.println(a.getName());
                System.out.println(a.getBirthYear());
                System.out.println(a.getNationality());
            }
            Author author1 = authors.get(0);

            EBook eBook = new EBook(44, "nameme", author1, 1990, "11223344556", 2.4, "internet.com");
            bookService.creteEbook(eBook);

            List<Book> books = bookService.getAllBooks();
            System.out.println("Total books: " + books.size());
            for (Book b : books) {
                System.out.println(b.getTitle());
                System.out.println(b.getYear());
            }
            bookService.deleteBookID(2);
            System.out.println("Book with id 2 deleted");


        } catch (Exception e) {
            System.err.println("Error" + e.getMessage());
            e.printStackTrace();
        }


    }
}