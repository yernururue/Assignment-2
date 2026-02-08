package com.library.service;

import com.library.dto.BookDTO;
import com.library.exception.ResourceNotFoundException;
import com.library.model.*;
import com.library.patterns.BookFactory;
import com.library.repository.AuthorRepository;
import com.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Book createBook(BookDTO dto) {
        Author author = authorRepository.findById(dto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + dto.getAuthorId()));

        Book book = BookFactory.createBook(dto.getBookType(), dto, author);

        return bookRepository.save(book);
    }

    public Book updateBook(Long id, BookDTO dto) {
        Book existingBook = getBookById(id);

        existingBook.setTitle(dto.getTitle());
        existingBook.setIsbn(dto.getIsbn());
        existingBook.setYear(dto.getYear());
        existingBook.setAvailable(dto.isAvailable());

        if (dto.getAuthorId() != null) {
            Author author = authorRepository.findById(dto.getAuthorId())
                    .orElseThrow(() -> new ResourceNotFoundException("Author not found"));
            existingBook.setAuthor(author);
        }

        if (existingBook instanceof PhysicalBook) {
            PhysicalBook pb = (PhysicalBook) existingBook;
            if (dto.getShelfLocation() != null)
                pb.setShelfLocation(dto.getShelfLocation());
            if (dto.getWeight() != null)
                pb.setWeight(dto.getWeight());
        } else if (existingBook instanceof EBook) {
            EBook eb = (EBook) existingBook;
            if (dto.getFileFormat() != null)
                eb.setFileFormat(dto.getFileFormat());
            if (dto.getDownloadLink() != null)
                eb.setDownloadLink(dto.getDownloadLink());
        }

        return bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }

    public List<Book> searchByTitle(String keyword) {
        return bookRepository.findByTitleContainingIgnoreCase(keyword);
    }

    public List<Book> getAvailableBooks() {
        return bookRepository.findByAvailableTrue();
    }
}
