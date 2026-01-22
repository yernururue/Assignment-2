package service;
import model.Author;
import utils.DatabaseConnection;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
//DAO (Data Access Object)

public class AuthorDAO {
    public void addAuthor(Author author) {
        String sql = "insert into authors(name, birthyear, nationality) values(?,?,?)";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setString(1, author.getName());
            pst.setInt(2, author.getBirthYear());
            pst.setString(3, author.getNationality());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Author getAuthorByID(int id) {
        String sql = "select * from authors where id = ?";
        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement pst = conn.prepareStatement(sql);) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                int authorID = rs.getInt("id");
                String name = rs.getString("name");
                int birthyear = rs.getInt("birthyear");
                String nationality = rs.getString("nationality");
                return new Author(authorID, name, birthyear, nationality);
            }
            else {
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    public List<Author> getAllAuthors() {
        String sql = "select * from authors";
        List<Author> authors = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);) {
            ResultSet rs = pst.executeQuery();

            while (rs.next()) {
                int authorId = rs.getInt("id");
                String name = rs.getString("name");
                String nationality = rs.getString("nationality");
                Author author = new Author(authorId, name, 0, nationality);
                authors.add(author);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
        return authors;
    }

    public void updateAuthor(Author author) {
        String sql = "update authors set name = ?,birthyear = ?, nationality = ? where id = ?";
        try (Connection connection = DatabaseConnection.getConnection(); PreparedStatement pst = connection.prepareStatement(sql)) {
            pst.setString(1, author.getName());
            pst.setInt(2, author.getBirthYear());
            pst.setString(3, author.getNationality());
            pst.setInt(4, author.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void deleteAuthor(int id) {
        String sql = "delete from authors where id = ?";
        try (Connection connection = DatabaseConnection.getConnection();
        PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setInt(1, id);
            pst.executeUpdate();
        }
        catch (SQLException e) {
            if (e.getMessage().contains("foreign key constraint")) {
                System.err.println("You can't delete an author that has book in library.");
                System.err.println("\nPlease, try to delete book with this author first");
            }
            System.err.println("Error: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
