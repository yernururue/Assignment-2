package service;
import exception.*;
import model.*;
import java.util.List;

public class BookService {

    private BookDAO bookdao = new BookDAO();
    private AuthorDAO authordao = new AuthorDAO();


    public void creteEbook(EBook ebook)
            throws InvalidInputException, DuplicateResourceException, ResourceNotFoundException {
        if (ebook.getTitle() == null || ebook.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Title cannot be empty");
        }
        if (ebook.getId() < 0) {
            throw new InvalidInputException("ID cannot be negative");
        }
        if (ebook.getFileSize() < 0) {
            throw new InvalidInputException("File size cannot be negative");
        }
        Author author = authordao.getAuthorByID(ebook.getAuthor().getId());
        if (author == null) {
            throw new ResourceNotFoundException("Author not found");
        }
        bookdao.addEBook(ebook);
    }

    public void createPrintedBook(printedBook printedBook) throws InvalidInputException, DuplicateResourceException, ResourceNotFoundException {
        if (printedBook.getTitle() == null || printedBook.getTitle().trim().isEmpty()) {
            throw new InvalidInputException("Title cannot be empty");
        }
        if (printedBook.getYear() < 0) {
            throw new InvalidInputException("Year cannot be negative");
        }
        if (printedBook.getWeight() < 0) {
            throw new InvalidInputException("Weight cannot be negative");
        }

        Author author = authordao.getAuthorByID(printedBook.getAuthor().getId());
        if (author==null) {
            throw new ResourceNotFoundException("Author not found");
        }
        bookdao.addPrintedBook(printedBook);
    }
    public Book getBookByID(int id) throws ResourceNotFoundException {
        Book book = bookdao.getBookByID(id);
        if (book == null) {
            throw new ResourceNotFoundException("Book not found");
        }
        return book;
    }

    public void deleteBookID(int id) throws ResourceNotFoundException {
        Book book = getBookByID(id);
        bookdao.deleteBookById(id);
    }
    public List<Book> getAllBooks() {
        return bookdao.getAllBooks();
    }

}