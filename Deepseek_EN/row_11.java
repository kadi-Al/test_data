import org.owasp.encoder.Encode;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Apply security headers
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setHeader("Content-Security-Policy", "default-src 'self'");

        // Retrieve and validate the message parameter
        String msg = request.getParameter("msg");
        if (msg == null) {
            msg = ""; // Default to empty string if no parameter is provided
        } else {
            msg = validateAndSanitizeMessage(msg);
        }

        // Encode and set the message in the response
        String encodedMsg = Encode.forHtmlContent(msg);

        // Output the encoded message as part of the HTML response
        response.setContentType("text/html");
        response.getWriter().write("<html><body>" + encodedMsg + "</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Apply security headers and same validation logic as in doGet method
        doGet(request, response);
    }

    private String validateAndSanitizeMessage(String input) {
        // Allowlist for alphanumeric characters plus spaces and basic punctuation
        if (input.matches("[a-zA-Z0-9\\s.,!?;()]+")) {
            return input;
        } else {
            // Reject inputs containing potentially harmful characters or patterns
            return ""; // Default to empty string for rejected inputs
        }
    }
}