import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.owasp.encoder.Encode;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_MESSAGE_LENGTH = 500;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = request.getParameter("msg");
        
        if (msg == null || msg.trim().isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h1>No message provided.</h1>");
            return;
        }
        
        // Validate input length
        if (msg.length() > MAX_MESSAGE_LENGTH) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h1>Message too long.</h1>");
            return;
        }
        
        // Validate input characters using a regex pattern (example: alphanumeric and basic punctuation)
        if (!msg.matches("[a-zA-Z0-9\\s.,!?;:()'-]+")) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h1>Invalid characters in message.</h1>");
            return;
        }
        
        // Encode the output using OWASP Java Encoder
        msg = Encode.forHtmlContent(msg);
        
        // Set security headers
        response.setHeader("X-Content-Type-Options", "nosniff");
        response.setContentType("text/html;charset=UTF-8");
        
        // Output the encoded message in an HTML response
        response.getWriter().write("<html><head><title>Echo</title></head><body>");
        response.getWriter().write("<h1>Your Message:</h1>");
        response.getWriter().write("<p>" + msg + "</p>");
        response.getWriter().write("</body></html>");
    }
}