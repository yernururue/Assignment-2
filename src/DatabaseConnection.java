import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    // These should be private constants
    private static final String url = "jdbc:postgresql://localhost:5432/librarydb";
    private static final String user = "postgres";
    private static final String password = "1234";

    private static Connection connection = null;

    // Private constructor prevents other classes from creating new instances
    private DatabaseConnection() {}

    public static Connection getConnection() throws SQLException {
        // If connection doesn't exist or was closed, create a new one
        if (connection == null || connection.isClosed()) {
            try {
                // Optional: Load the driver explicitly if using older Java versions
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Database connected successfully!");
            } catch (ClassNotFoundException e) {
                throw new SQLException("PostgreSQL Driver not found", e);
            }
        }
        return connection;
    }
}