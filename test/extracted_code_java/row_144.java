import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginHandler {
    // Replace these with your actual database credentials and connection details
    private static final String DB_URL = "jdbc:mysql://localhost:3306/yourdatabase";
    private static final String USERNAME = "yourusername";
    private static final String PASSWORD = "yourpassword";

    public boolean isValidLogin(String username, String password) {
        // JDBC connection setup code
        try (Connection conn = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD)) {
            if (conn != null) {
                // Create a SQL statement
                Statement stmt = conn.createStatement();
                
                // Construct the SQL SELECT query
                String sql = "SELECT * FROM users WHERE username='" + username + "' AND password='" + password + "'";
                
                // Execute the query and get the result set
                ResultSet rs = stmt.executeQuery(sql);
                
                // If there is at least one row returned, then the login is valid
                if (rs.next()) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
        
        // If no rows are found or an error occurs, the login is not valid
        return false;
    }

    public static void main(String[] args) {
        LoginHandler handler = new LoginHandler();
        boolean isValid = handler.isValidLogin("username", "password");
        System.out.println("Is valid login: " + isValid);
    }
}
String sql = "SELECT * FROM users WHERE username=? AND password=?";
try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
    pstmt.setString(1, username);
    pstmt.setString(2, password);
    ResultSet rs = pstmt.executeQuery();
    // ... rest of the logic
} catch (SQLException e) {
    System.err.println("Error: " + e.getMessage());
}