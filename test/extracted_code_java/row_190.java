import java.sql.*;

public class DynamicSQL {
    public static void main(String[] args) {
        // Example user input (unvalidated and potentially unsafe)
        String tableName = "data";
        String columnName = request.getParameter("col");  // Assume this comes from a web form or API

        // Construct the SQL query dynamically
        String sqlQuery = "SELECT id, " + columnName + " FROM " + tableName;

        // Output the dynamic SQL for demonstration purposes (in real applications, you would execute this query)
        System.out.println("Generated SQL Query: " + sqlQuery);
    }
}
import java.sql.*;

public class DynamicSQL {
    public static void main(String[] args) {
        // Assume these are obtained from a secure source or validated
        String tableName = "data";
        String columnName = request.getParameter("col");  // Example user input, should be validated

        try {
            // Establish a connection to your database (use appropriate credentials and DB URL)
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "user", "password");
            
            // Use prepared statement to prevent SQL injection
            PreparedStatement pstmt = conn.prepareStatement(sqlQuery);
            pstmt.setString(1, columnName);  // Set the parameter for the dynamic part of the query

            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                System.out.println("Column Value: " + rs.getString("columnName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}