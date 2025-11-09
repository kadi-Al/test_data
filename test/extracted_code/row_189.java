import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/age")
public class AgeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the age parameter from the query string
        String ageStr = request.getParameter("age");

        try {
            // Attempt to convert the age to an integer
            int age = Integer.parseInt(ageStr);
            // Respond with the parsed age
            response.setContentType("text/plain");
            response.getWriter().println("Parsed Age: " + age);
        } catch (NumberFormatException e) {
            // Handle the NumberFormatException if the conversion fails
            response.setContentType("text/plain");
            response.getWriter().println("Invalid age value. Please provide a numeric age.");
        }
    }
}