package service;
import model.Author;
import model.Book;
import model.EBook;
import model.printedBook;
import utils.DatabaseConnection;

import java.sql.*;

//add book
//update book
//get book by id
//get all books
//delete book

public class BookDAO {


    public void addEBook(EBook book) {
        String sql = "insert into books(title, isbn,author_id, publish_year, book_type, download_url, file_size) values(?, ?, ?,?,'EBOOK',?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setInt(3,book.getAuthor().getId());
            ps.setInt(4, book.getYear());
            ps.setString(5, book.getDownloadURL());
            ps.setDouble(6, book.getFileSize());
            ps.setBoolean(7,book.isAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

   public void addPrintedBook(printedBook book) {
        String sql = "insert into books(title, isbn, author_id, publish_year, book_type, shelf_location, weight, available) " +
                "values(?, ?, ?, ?, 'PRINTED', ?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setInt(3, book.getAuthor().getId());
            ps.setInt(4,book.getYear());
            ps.setString(5, book.getShelfLocation());
            ps.setDouble(6, book.getWeight());
            ps.setBoolean(7, book.isAvailable());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
   }

    public Book getBookByID(int id) {
        String sql = "select * from books where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
            PreparedStatement ps = connection.prepareStatement(sql))  {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();


            if (rs.next()) {
                String bookType = rs.getString("book_type");

                if ("EBOOK".equals(bookType)) {
                    return createEBookFromResultSet(rs);
                } else if("PRINTED".equals((bookType))) {
                    return createPrintedBookFromResultSet(rs);
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error" + e.getMessage());
            return null;
        }
        return null;
    }

    public EBook createEBookFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String isbn = rs.getString("isbn");
        int authorId = rs.getInt("author_id");
        int year = rs.getInt("publish_year");
        String downloadUrl = rs.getString("download_url");
        double fileSize = rs.getDouble("file_size");

        // Get author (you'll need AuthorDAO for this)
        AuthorDAO authorDAO = new AuthorDAO();
        Author author = authorDAO.getAuthorByID(authorId);

        EBook ebook = new EBook(id, title, author, year, isbn, fileSize, downloadUrl);
        return ebook;
    }

    public printedBook createPrintedBookFromResultSet(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String title = rs.getString("title");
        String isbn = rs.getString("isbn");
        int authorId = rs.getInt("author_id");
        int year = rs.getInt("publish_year");
        String shelflocation = rs.getString("shelf_location");
        double weight = rs.getDouble("weight");

        AuthorDAO authorDAO = new AuthorDAO();
        Author author = authorDAO.getAuthorByID(authorId);
        printedBook pbook = new printedBook(id, title, author, year, isbn, shelflocation, weight);
        return pbook;
    }
}
