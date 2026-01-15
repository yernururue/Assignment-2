package model;

public class EBook extends model.Book implements DigitalAccess{
    private String downloadURL;
    private double fileSize;
    private boolean available = true;
    protected double filesize;
    protected String downloadUrl;
    public EBook(int id, String name, String author, int year, double filesize, String downloadUrl) {
        super(id, name, author, year);
        this.downloadUrl = downloadUrl;
        this.filesize = filesize;

    }

    @Override
    public double calculateLateFee(int days) {
        return days;
    }

    @Override
    public String getAccessInstructions() {
        return "";
    }


    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String getDownloadURL() {
        return downloadURL;
    }

    @Override
    public double getFileSize() {
        return fileSize;
    }
}
