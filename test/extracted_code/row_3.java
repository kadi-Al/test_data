import java.sql.*;
import java.util.Scanner;

public class LoginApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Database credentials (replace with actual database details)
        String url = "jdbc:mysql://localhost:3306/your_database";
        String usernameDB = "root";
        String passwordDB = "password";
        
        System.out.println("Enter your username:");
        String userInputUsername = scanner.nextLine();
        
        System.out.println("Enter your password:");
        String userInputPassword = scanner.nextLine();
        
        // Check if the entered credentials match with those in the database
        boolean isValidUser = checkCredentials(userInputUsername, userInputPassword, url, usernameDB, passwordDB);
        
        if (isValidUser) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }
    
    public static boolean checkCredentials(String inputUsername, String inputPassword, String url, String userDB, String passDB) {
        try (Connection conn = DriverManager.getConnection(url, userDB, passDB)) {
            if (conn != null) {
                // Query to check if the username and password exist in the database
                String query = "SELECT * FROM users WHERE username=? AND password=?";
                
                PreparedStatement pstmt = conn.prepareStatement(query);
                pstmt.setString(1, inputUsername);
                pstmt.setString(2, inputPassword);
                
                ResultSet rs = pstmt.executeQuery();
                
                if (rs.next()) { // If there's a matching username and password in the database
                    return true;
                }
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        
        return false;
    }
}