import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/redirect")
public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the next URL parameter from the request
        String nextUrl = request.getParameter("next");
        
        if (nextUrl != null && !nextUrl.isEmpty()) {
            // Perform a simple redirect to the next URL
            response.sendRedirect(nextUrl);
        } else {
            // If no or empty 'next' parameter is provided, you might want to handle this case differently
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Missing or invalid 'next' parameter.");
        }
    }
}