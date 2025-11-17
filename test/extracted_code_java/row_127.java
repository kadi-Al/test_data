import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/reflectError")
public class ReflectErrorServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the error parameter from the request
        String errParam = request.getParameter("err");

        if (errParam != null && !errParam.isEmpty()) {
            // Set the Location header with the error parameter
            response.setHeader("Location", "/page?err=" + errParam);
            // Set the status code to 302 Found
            response.setStatus(HttpServletResponse.SC_FOUND);
        } else {
            // Handle the case where there is no error parameter
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing 'err' parameter");
        }
    }
}