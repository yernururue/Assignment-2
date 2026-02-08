package com.library.patterns;

import com.library.model.*;
import com.library.dto.BookDTO;

public class BookFactory {

    public static Book createBook(String type, BookDTO dto, Author author) {
        if (type == null) {
            throw new IllegalArgumentException("Book type cannot be null");
        }

        switch (type.toUpperCase()) {
            case "PHYSICAL":
                return createPhysicalBook(dto, author);
            case "EBOOK":
                return createEBook(dto, author);
            default:
                throw new IllegalArgumentException(
                        "Unknown book type: " + type + ". Supported types: PHYSICAL, EBOOK");
        }
    }

    private static PhysicalBook createPhysicalBook(BookDTO dto, Author author) {
        PhysicalBook book = new PhysicalBook();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
        book.setAuthor(author);
        book.setAvailable(true);
        book.setShelfLocation(dto.getShelfLocation());
        book.setWeight(dto.getWeight());
        return book;
    }

    private static EBook createEBook(BookDTO dto, Author author) {
        EBook book = new EBook();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setYear(dto.getYear());
        book.setAuthor(author);
        book.setAvailable(true);
        book.setFileFormat(dto.getFileFormat());
        book.setDownloadLink(dto.getDownloadLink());
        return book;
    }

    public static String[] getSupportedTypes() {
        return new String[] { "PHYSICAL", "EBOOK" };
    }
}
