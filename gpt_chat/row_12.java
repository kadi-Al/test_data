import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.validator.routines.InetAddressValidator;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

@WebServlet("/checkHost")
public class CheckHostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String host = request.getParameter("host");

        // Validate the input to ensure it is a valid IP address or hostname
        if (!InetAddressValidator.getInstance().isValid(host)) {
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Invalid host format.");
            return;
        }

        // Perform network connectivity check using Apache HttpClient
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpGet requestCheckHost = new HttpGet("http://" + host);
            HttpResponse httpResponse = httpClient.execute(requestCheckHost);

            // Read the response and return it to the client
            HttpEntity entity = httpResponse.getEntity();
            String content = EntityUtils.toString(entity, "UTF-8");
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Response from host: " + content);
        } catch (Exception e) {
            // Log and handle exceptions securely without exposing stack traces
            request.setAttribute("errorMessage", "An error occurred while checking the host.");
            request.getRequestDispatcher("/errorHandler").forward(request, response);
        }
    }
}