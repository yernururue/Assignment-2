package model;

public class Author {
    private int id;
    private String name;
    private int year;
    private String nationality;
    public Author(int id, String name, int year, String nationality) {
        this.id = id;
        this.name = name;
        this.year = year;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getYear() {
        return year;
    }
    public String getNationality() {
        return nationality;
    }

    public void setId(int id) {
        if (id > 0) {
            this.id = id;
        }
    }

    public void setName(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        } this.name = name;
    }

    public void setYear(int year) {
        if (year <0 || year>2026) {
            throw new IllegalArgumentException("Invalid year.");
        } this.year = year;
    }

    public void setNationality(String nationality) {
        if (nationality == null || nationality.trim().isEmpty()) {
            throw new IllegalArgumentException("Nationality cannot be empty.");
        } this.nationality = nationality;
    }
}
