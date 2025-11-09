import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RedirectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the user input for the redirect location
        String redirectTo = request.getParameter("redirect_to");

        if (redirectTo != null && !redirectTo.isEmpty()) {
            // Set the Location header to the user-provided URL
            response.setHeader("Location", redirectTo);
            
            // Send a 302 status code and include the Location header in the response
            response.sendRedirect(redirectTo);
        } else {
            // If no valid redirect URL is provided, you might want to handle this case differently
            response.setContentType("text/html");
            response.getWriter().write("<h1>Invalid Redirect URL</h1>");
        }
    }
}