package model;

public class printedBook extends Book implements Borrowable{
    private boolean available = true;
    protected String shelfLocation;
    protected double weight;

    public printedBook(int id, String name, String author, int year, String shelfLocation, double weight) {
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
}
