package model;

public class printedBook extends Book implements Borrowable{
    private boolean available = true;
    private String shelfLocation;
    private double weight;
    private double latefee = 0.5;


    public printedBook(int id, String name, Author author, int year, String isbn, String shelfLocation, double weight) {
        super(id, name, author, year, isbn);
        this.shelfLocation = shelfLocation;
        this.weight = weight;
    }

    @Override
    public double calculateLateFee(int days) {
        if (days <= 0) return 0;
        return days * latefee;
    }

    @Override
    public String getAccessInstructions() {
        return "Located at: " + shelfLocation;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void borrow() {
        if (!available) {
            System.out.println("Book is already borrowed.");
            return;
        } available = false;
    }

    @Override
    public void returnItem() {
        if (!available) {
            available = true;
        }
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

    public double getLatefee() {
        return latefee;
    }

    public void setLatefee(double latefee) {
        this.latefee = latefee;
    }
}
