package model;

public class EBook extends model.Book{
    protected double filesize;
    protected String downloadUrl;
    public EBook(int id, String name, String author, int year, double filesize, String downloadUrl) {
        super(id, name, author, year);
        this.downloadUrl = downloadUrl;
        this.filesize = filesize;

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
