package model;


public abstract class Book{
    protected int id;
    protected String name;
    protected String author;
    protected int year;
//    private Book book1 = new printedBook(1, "To Kill a Mockingbird", "Harper Lee", 1990, "1row", 800);

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }
    //getters and setters encapsulation
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAuthor() {
        return author;
    }
    public int getYear() {
        return year;
    }

    public void setId(int id) {
        if (id>0) {
            this.id = id;
        }

    }
    public void setName(String name) {
        if (name != null) {
            this.name = name;

        }
    }
    public void setAuthor(String author) {
        if (author != null) {
            this.author = author;
        }
    }
    public void setYear(int year) {
        if (year>0) {
            this.year = year;
        }
    }

    //abstract methods
    public abstract double calculateLateFee(int days);
    public abstract String getAccessInstructions();
    //concrete method
    public void displayInfo() {
        System.out.println("ID: " + id +
                            "Name: " + name +
                            "Author: " + author +
                            "Year: " + year);
    }

}
