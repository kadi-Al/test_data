import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SecureServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the msg parameter from the request
        String userInput = request.getParameter("msg");

        // Validate and sanitize the input to prevent injection attacks
        if (userInput == null || userInput.trim().isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<p>Invalid input.</p>");
            out.println("</body></html>");
        } else {
            // Sanitize the input by removing potentially harmful characters or patterns
            String sanitizedInput = sanitizeInput(userInput);

            // Encode output with HTML escaping to prevent reflected XSS
            response.setContentType("text/html;charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<p>" + escapeHtml(sanitizedInput) + "</p>");
            out.println("</body></html>");
        }
    }

    private String sanitizeInput(String input) {
        // Implement a simple sanitization method to remove or replace potentially harmful characters
        return input.replaceAll("[\\W]", ""); // Example: removes all non-word characters
    }

    private String escapeHtml(String html) {
        StringBuilder out = new StringBuilder();
        for (char c : html.toCharArray()) {
            switch (c) {
                case '&': out.append("&amp;"); break;
                case '<': out.append("&lt;"); break;
                case '>': out.append("&gt;"); break;
                default: out.append(c); break;
            }
        }
        return out.toString();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // The same validation and sanitization logic will apply to POST requests as well
        doGet(request, response);
    }
}