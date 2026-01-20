package service;
import model.Author;
import model.Book;
import model.EBook;
import model.printedBook;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class BookDAO {
    public void addEBook(EBook book) {
        String sql = "insert into books(title, isbn,author_id, publish_year, book_type, download_url, file_size) values(?,?,?,?,'EBOOK',?,?)";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getIsbn());
            ps.setInt(3,book.getAuthor().getId());
            ps.setInt(4, book.getYear());
            ps.setString(5, book.getDownloadURL());
            ps.setDouble(6, book.getFileSize());
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

    public List<Book> getAllBooks() {
        String sql = "select * from books";
        List<Book> books = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String bookType = rs.getString("book_type");

                if ("EBOOK".equals(bookType)) {
                    books.add(createEBookFromResultSet(rs));
                } else if ("PRINTED".equals(bookType)) {
                    books.add(createPrintedBookFromResultSet(rs));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return books;

    }


    public void updateBookByID(int id, String newTitle, String newIsbn, int newAuthorId, int newPublishYear, double newFileSize,String newDownloadUrl ,String newShelfLocation, double newWeight) {
        String sql1 = "update table books set title = ?,isbn =?, author_id = ?, publish_year = ?, file_size=?,download_url = ?, shelf_locaion=?, weight = ?  where id = ?";
        String sql2 = "select book_type from books where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement psCheckId = connection.prepareStatement(sql2);
        PreparedStatement psUpdate = connection.prepareStatement(sql1);) {

            String book_type;
            psCheckId.setInt(1, id);
            ResultSet rs = psCheckId.executeQuery();
            if (rs.next()) {
                book_type = rs.getString("book_type");
                if ("EBOOK".equals(book_type)) {
                    psUpdate.setString(1, newTitle);
                    psUpdate.setString(2, newIsbn);
                    psUpdate.setInt(3, newAuthorId);
                    psUpdate.setInt(4, newPublishYear);
                    psUpdate.setDouble(5,newFileSize);
                    psUpdate.setString(6, newDownloadUrl);
                    psUpdate.setNull(7, Types.DOUBLE);
                    psUpdate.setNull(8, Types.VARCHAR); //null because ebook does not have shelflocation and weight

                    //id of a book
                    psUpdate.setInt(9, id);
                }
                else if ("PRINTEDBOOK".equals(book_type)) {
                    psUpdate.setString(1, newTitle);
                    psUpdate.setString(2, newIsbn);
                    psUpdate.setInt(3, newAuthorId);
                    psUpdate.setInt(4, newPublishYear);
                    psUpdate.setNull(5, Types.DOUBLE); //null because printed book does not have filesize and download url
                    psUpdate.setNull(6, Types.VARCHAR);
                    psUpdate.setString(7,newShelfLocation);
                    psUpdate.setDouble(8, newWeight);
                    //id of a book
                    psUpdate.setInt(9, id);
                }
                else {
                    return;
                }
            }
            int rowsAffected = psUpdate.executeUpdate();
            if(rowsAffected>0) {
                System.out.println("Book updated successfully");
            } else {
                System.out.println("There is no book with this id");
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteBookById(int id) {
        String sql = "delete from books where id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }
}
