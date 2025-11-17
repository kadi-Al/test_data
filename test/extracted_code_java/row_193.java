html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Sort Form</title>
</head>
<body>
    <form action="sortTable" method="get">
        <label for="sort">Sort by:</label>
        <select id="sort" name="sort">
            <option value="column1 ASC">Column 1 Ascending</option>
            <option value="column2 DESC">Column 2 Descending</option>
            <!-- Add more options as needed -->
        </select>
        <button type="submit">Sort</button>
    </form>
</body>
</html>
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*; // Assuming database interaction

public class SortHandler extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sort = request.getParameter("sort");
        if (sort != null && !sort.isEmpty()) {
            try {
                // Database connection and query execution logic here
                Connection conn = getConnection(); // Assume method to get DB connection
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM your_table " + sort);
                
                // Process the result set, for example, print out each row
                while (rs.next()) {
                    System.out.println(rs.getString("column1") + ", " + rs.getString("column2"));
                }
                
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No sort parameter provided.");
        }
    }
    
    private Connection getConnection() {
        // Implement your DB connection logic here
        return null;
    }
}
jsp
<%@ page import="java.sql.*, java.util.*" %>
<% 
    String sort = request.getParameter("sort");
    if (sort != null && !sort.isEmpty()) {
        try {
            Connection conn = getConnection(); // Assume method to get DB connection
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM your_table " + sort);
            
            while (rs.next()) {
                out.println(rs.getString("column1") + ", " + rs.getString("column2") + "<br>");
            }
            
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    } else {
        out.println("No sort parameter provided.");
    }
%>