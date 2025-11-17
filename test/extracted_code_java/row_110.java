import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/echo")
public class EchoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the message parameter from the request
        String msg = request.getParameter("msg");
        
        // Set content type to HTML
        response.setContentType("text/html;charset=UTF-8");
        
        // Create a PrintWriter for outputting directly to the client
        PrintWriter out = response.getWriter();
        
        // Generate an HTML page that echoes back the message
        out.println("<html>");
        out.println("<head><title>Echo</title></head>");
        out.println("<body>");
        out.println("<h1>Message: " + msg + "</h1>");  // Echoing back the message directly, potentially vulnerable to XSS
        out.println("</body>");
        out.println("</html>");
    }
}