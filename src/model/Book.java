package model;

import exception.InvalidInputException;

public abstract class Book{
    protected int id;
    protected String title;
    protected int year;
    protected Author author;
    protected String isbn;
    protected String book_type;

    public Book(int id, String name, Author author, int year, String isbn) {
        this.id = id;
        this.title = name;
        this.year = year;
        this.author = author;
        this.isbn = isbn;
    }

    //getters and setters encapsulation
    public int getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public int getYear() {
        return year;
    }
    public Author getAuthor() {
        return author;
    }
    public String getIsbn() {return isbn;}
    public String getBook_type() {
        return book_type;
    }
    public void setId(int id) {
        if (id>0) {
            this.id = id;
        }

    }
    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty"); //validation logic
        }
        this.title = title;
    }
    public void setYear(int year) {
        if (year<0 || year > 2026) {
            throw new IllegalArgumentException("Invalid year");
        } this.year = year;
    }
    public void setAuthor(Author author) {
        if (author == null){
            throw new IllegalArgumentException("Author cannot be null");
        }
        this.author = author;
    }
    public void setBook_type(String bookType) {
        if (bookType == null) {
            throw new IllegalArgumentException("book type cannot be null");
        }
        this.book_type = bookType;
    }

    //abstract methods
    public abstract double calculateLateFee(int days);
    public abstract String getAccessInstructions();
    //concrete method
    public void displayInfo() {
        System.out.println("ID: " + id +
                            "\nName: " + title +
                            "\nAuthor: " + author.getName()+
                            "\nYear: " + year +
                            "\nUniqueness: " + isbn);
    }

}
