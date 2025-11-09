import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/jsonGenerator")
public class JsonGeneratorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve user input from query parameters
        String name = request.getParameter("name");
        String ageStr = request.getParameter("age");
        int age = (ageStr != null && !ageStr.isEmpty()) ? Integer.parseInt(ageStr) : -1; // Default to -1 if not provided or invalid

        // Build JSON response manually using string concatenation
        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("{");
        jsonResponse.append("\"name\":\"");
        jsonResponse.append(name != null ? name : "Unknown"); // Default to "Unknown" if no name is provided
        jsonResponse.append("\",");
        jsonResponse.append("\"age\":");
        jsonResponse.append(age);
        jsonResponse.append("}");

        // Set content type to JSON
        response.setContentType("application/json");
        // Write the JSON string to the response
        response.getWriter().write(jsonResponse.toString());
    }
}