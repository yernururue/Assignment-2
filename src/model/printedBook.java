package model;

public class printedBook extends Book implements Borrowable{
    private boolean available = true;
    private String shelfLocation;
    private double weight;


    public printedBook(int id, String name, Author author, int year, String shelfLocation, double weight) {
        super(id, name, author, year);
        this.shelfLocation = shelfLocation;
        this.weight = weight;
    }

    @Override
    public double calculateLateFee(int days) {
        return 0;
    }

    @Override
    public String getAccessInstructions() {
        return "";
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        if (!available) {
            System.out.println("Book is already borrowed.");
        } available = false;
    }

    @Override
    public void returnItem() {
        available = true;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getShelfLocation() {
        return shelfLocation;
    }

    public void setShelfLocation(String shelfLocation) {
        if (shelfLocation == null || shelfLocation.trim().isEmpty()) {
            throw new IllegalArgumentException("Location cannot be empty.");
        }
        this.shelfLocation = shelfLocation;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        if (weight<0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }
        this.weight = weight;
    }
}
