package model;


public abstract class Book{
    protected int id;
    protected String title;
    protected int year;
    protected Author author;
    protected boolean isbn;
//    private Book book1 = new printedBook(1, "To Kill a Mockingbird", "Harper Lee", 1990, "1row", 800);

    public Book(int id, String name, Author author, int year, boolean isbn) {
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
        if (author != null){
            this.author = author;
        }
    }

    //abstract methods
    public abstract double calculateLateFee(int days);
    public abstract String getAccessInstructions();
    //concrete method
    public void displayInfo() {
        System.out.println("ID: " + id +
                            "Name: " + title +
                            "Author: " + author.getName()+
                            "Year: " + year +
                            "Uniqueness: " + isbn);
    }

}
