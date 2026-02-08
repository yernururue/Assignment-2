package com.library.patterns;

import com.library.model.Book;
import com.library.model.Borrowing;
import java.time.LocalDate;

public class BorrowingBuilder {

    private Book book;
    private String memberName;
    private LocalDate borrowDate;
    private LocalDate returnDate;
    private String status;

    public BorrowingBuilder() {
        this.borrowDate = LocalDate.now();
        this.status = "ACTIVE";
    }

    public BorrowingBuilder withBook(Book book) {
        this.book = book;
        return this;
    }

    public BorrowingBuilder withMember(String memberName) {
        this.memberName = memberName;
        return this;
    }

    public BorrowingBuilder withBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
        return this;
    }

    public BorrowingBuilder withReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
        return this;
    }

    public BorrowingBuilder withStatus(String status) {
        this.status = status;
        return this;
    }

    public BorrowingBuilder withDurationDays(int days) {
        this.returnDate = this.borrowDate.plusDays(days);
        return this;
    }

    public Borrowing build() {
        validate();
        return new Borrowing(book, memberName, borrowDate, returnDate, status);
    }

    private void validate() {
        if (book == null) {
            throw new IllegalStateException("Book is required for borrowing");
        }
        if (memberName == null || memberName.trim().isEmpty()) {
            throw new IllegalStateException("Member name is required");
        }
        if (borrowDate == null) {
            throw new IllegalStateException("Borrow date is required");
        }
    }

    public BorrowingBuilder reset() {
        this.book = null;
        this.memberName = null;
        this.borrowDate = LocalDate.now();
        this.returnDate = null;
        this.status = "ACTIVE";
        return this;
    }
}
