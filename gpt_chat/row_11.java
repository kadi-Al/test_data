xml
    <dependency>
        <groupId>org.owasp</groupId>
        <artifactId>encoder</artifactId>
        <version>1.2.3</version>
    </dependency>
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve and sanitize the input parameter "msg"
        String msg = request.getParameter("msg");

        if (msg == null || msg.isEmpty()) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("No message received.");
            return;
        }

        // Validate the input to ensure it contains only alphanumeric characters and spaces (basic validation)
        if (!msg.matches("[a-zA-Z0-9 ]+")) {
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("Invalid message content.");
            return;
        }

        // Encode the input to prevent XSS attacks
        String encodedMsg = Encode.forHtml(msg);

        try {
            // Write the encoded and sanitized message back to the client
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("<h1>Echoed Message:</h1>" + encodedMsg);
        } catch (Exception e) {
            // Handle unexpected errors gracefully by showing a generic error message
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write("An error occurred while processing your request.");
        }
    }
}