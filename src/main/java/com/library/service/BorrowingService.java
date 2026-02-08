package com.library.service;

import com.library.dto.BorrowingDTO;
import com.library.exception.ResourceNotFoundException;
import com.library.model.Book;
import com.library.model.Borrowing;
import com.library.patterns.BorrowingBuilder;
import com.library.repository.BookRepository;
import com.library.repository.BorrowingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class BorrowingService {

    @Autowired
    private BorrowingRepository borrowingRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<Borrowing> getAllBorrowings() {
        return borrowingRepository.findAll();
    }

    public Borrowing getBorrowingById(Long id) {
        return borrowingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Borrowing not found with id: " + id));
    }

    public Borrowing createBorrowing(BorrowingDTO dto) {
        Book book = bookRepository.findById(dto.getBookId())
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + dto.getBookId()));

        if (!book.isAvailable()) {
            throw new IllegalStateException("Book is not available for borrowing");
        }

        BorrowingBuilder builder = new BorrowingBuilder()
                .withBook(book)
                .withMember(dto.getMemberName())
                .withBorrowDate(dto.getBorrowDate() != null ? dto.getBorrowDate() : LocalDate.now());

        if (dto.getReturnDate() != null) {
            builder.withReturnDate(dto.getReturnDate());
        } else if (dto.getDurationDays() != null) {
            builder.withDurationDays(dto.getDurationDays());
        } else {
            builder.withDurationDays(14);
        }

        Borrowing borrowing = builder.build();

        book.setAvailable(false);
        bookRepository.save(book);

        return borrowingRepository.save(borrowing);
    }

    public Borrowing returnBook(Long id) {
        Borrowing borrowing = getBorrowingById(id);

        Book book = borrowing.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        borrowing.setStatus("RETURNED");
        borrowing.setReturnDate(LocalDate.now());

        return borrowingRepository.save(borrowing);
    }

    public List<Borrowing> getBorrowingsByMember(String memberName) {
        return borrowingRepository.findByMemberName(memberName);
    }

    public List<Borrowing> getActiveBorrowings() {
        return borrowingRepository.findByStatus("ACTIVE");
    }
}
