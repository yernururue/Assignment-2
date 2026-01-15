package model;

public class realbook extends Book{

    protected String shelfLocation;
    protected double weight;

    public realbook(int id, String name, String author, int year, String shelfLocation, double weight) {
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
}
