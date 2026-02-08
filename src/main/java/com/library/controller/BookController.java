package com.library.controller;

import com.library.dto.BookDTO;
import com.library.model.Book;
import com.library.model.EBook;
import com.library.model.PhysicalBook;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);

        Map<String, Object> response = new HashMap<>();
        response.put("id", book.getId());
        response.put("title", book.getTitle());
        response.put("isbn", book.getIsbn());
        response.put("year", book.getYear());
        response.put("available", book.isAvailable());
        response.put("bookType", book.getBookType());
        response.put("accessInstructions", book.getAccessInstructions());

        if (book.getAuthor() != null) {
            Map<String, Object> authorInfo = new HashMap<>();
            authorInfo.put("id", book.getAuthor().getId());
            authorInfo.put("name", book.getAuthor().getName());
            response.put("author", authorInfo);
        }

        if (book instanceof PhysicalBook) {
            PhysicalBook pb = (PhysicalBook) book;
            response.put("shelfLocation", pb.getShelfLocation());
            response.put("weight", pb.getWeight());
        } else if (book instanceof EBook) {
            EBook eb = (EBook) book;
            response.put("fileFormat", eb.getFileFormat());
            response.put("downloadLink", eb.getDownloadLink());
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody BookDTO bookDTO) {
        Book created = bookService.createBook(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        Book updated = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, String>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Book deleted successfully");
        response.put("id", id.toString());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String keyword) {
        List<Book> books = bookService.searchByTitle(keyword);
        return ResponseEntity.ok(books);
    }

    @GetMapping("/available")
    public ResponseEntity<List<Book>> getAvailableBooks() {
        List<Book> books = bookService.getAvailableBooks();
        return ResponseEntity.ok(books);
    }
}
