package model;

public class EBook extends model.Book implements DigitalAccess{
    private String downloadURL;
    private double fileSize;
    private boolean available = true;
    protected double filesize;
    protected String downloadUrl;
    public EBook(int id, String name, Author author, int year, double filesize, String downloadUrl) {
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

    public boolean isAvailable() {
        return available;
    }

    @Override
    public Author getAuthor() {
        return super.getAuthor();
    }

    public double getFilesize() {
        return filesize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }
}
