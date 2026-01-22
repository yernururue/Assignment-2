package service;
import exception.*;
import model.*;
import java.util.List;

public class AuthorService {
    private AuthorDAO authordao = new AuthorDAO();

    public void createAuthor(Author author) throws InvalidInputException {
        if (author.getName() == null || author.getName().trim().isEmpty()) {
            throw new InvalidInputException("Name cannot be empty");
        }
        if (author.getBirthYear()<0) {
            throw new InvalidInputException("Birth year cannot be negative");
        }
        if (author.getNationality()== null|| author.getNationality().trim().isEmpty()) {
            throw new InvalidInputException("Nationality cannot be null");
        }
        authordao.addAuthor(author);
    }
    public Author getAuthorByID(int id) throws ResourceNotFoundException{
        Author author = authordao.getAuthorByID(id);
        if (author==null) {
            throw new ResourceNotFoundException("Author not found");
        }
        return author;
    }
    public List<Author> getAllAuthors() {
        return authordao.getAllAuthors();
    }

    public void deleteAuthorByID(int id) {
        Author author = authordao.getAuthorByID(id);
        authordao.deleteAuthor(id);
    }
 }

