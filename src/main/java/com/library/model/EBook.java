package com.library.model;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("EBOOK")
public class EBook extends Book {

    @Column(name = "file_format")
    private String fileFormat;

    @Column(name = "download_link")
    private String downloadLink;

    public EBook() {
        super();
    }

    public EBook(String title, String isbn, int year, Author author,
            String fileFormat, String downloadLink) {
        super(title, isbn, year, author);
        this.fileFormat = fileFormat;
        this.downloadLink = downloadLink;
    }

    @Override
    public String getAccessInstructions() {
        return "Download this " + fileFormat + " book from: " + downloadLink;
    }

    @Override
    public String getBookType() {
        return "EBOOK";
    }

    public String getFileFormat() {
        return fileFormat;
    }

    public void setFileFormat(String fileFormat) {
        this.fileFormat = fileFormat;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    @Override
    public String toString() {
        return String.format("EBook[id=%d, title='%s', format='%s']",
                getId(), getTitle(), fileFormat);
    }
}
