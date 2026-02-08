package com.library.repository;

import com.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String keyword);

    Optional<Book> findByIsbn(String isbn);

    List<Book> findByAvailableTrue();

    List<Book> findByAuthorId(Long authorId);
}
