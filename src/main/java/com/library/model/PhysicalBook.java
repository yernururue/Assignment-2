package com.library.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("PHYSICAL")
public class PhysicalBook extends Book {

    @Column(name = "shelf_location")
    private String shelfLocation;

    @Column
    private Double weight;

    public PhysicalBook() {
        super();
    }

    public PhysicalBook(String title, String isbn, int year, Author author,
            String shelfLocation, Double weight) {
        super(title, isbn, year, author);
        this.shelfLocation = shelfLocation;
        this.weight = weight;
    }

    @Override
    public String getAccessInstructions() {
        return "Find this book at shelf location: " + shelfLocation;
    }

    @Override
    public String getBookType() {
        return "PHYSICAL";
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        this.shelfLocation = shelfLocation;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return String.format("PhysicalBook[id=%d, title='%s', shelf='%s']",
                getId(), getTitle(), shelfLocation);
    }
}
