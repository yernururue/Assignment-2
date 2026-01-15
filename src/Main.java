import model.Book;
import model.printedBook;

public class Main {
    public static void main(String[] args) {
        Book book1 = new printedBook(1, "To Kill a Mockingbird", "Harper Lee", 1990, "1row", 800);
        book1.displayInfo();


    }
}