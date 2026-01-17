package model;

public class EBook extends model.Book implements DigitalAccess{
    private String downloadURL;
    private double fileSize;
    private boolean available = true;
    private double filesize;
    private String downloadUrl;
    private double latefee = 0.25;



    public EBook(int id, String name, Author author, int year, boolean isbn, double filesize, String downloadUrl) {
        super(id, name, author, year, isbn);
        this.downloadUrl = downloadUrl;
        this.filesize = filesize;

    }

    @Override
    public double calculateLateFee(int days) { // variable "days" is late days
        if (days <= 0) return 0;
        return days * latefee;
    }

    @Override
    public String getAccessInstructions() {
        return "Visit the website: " + downloadUrl;
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

    public double getLatefee() {
        return latefee;
    }

    public void setLatefee(double latefee) {
        if (latefee<0) {
            throw new IllegalArgumentException("It cannot be negative");
        }
        this.latefee = latefee;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        if (downloadUrl == null || downloadUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("Download url cannot be empty");
        }
        this.downloadUrl = downloadUrl;
    }

    public double getFilesize() {
        return filesize;
    }

    public void setFilesize(double filesize) {
        if (filesize<0) {
            throw new IllegalArgumentException("File size cannot be negative");
        }
        this.filesize = filesize;
    }
}
