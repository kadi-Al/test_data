import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@WebServlet("/ping")
public class PingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String host = request.getParameter("host");
        if (host == null || host.isEmpty()) {
            response.setContentType("text/html");
            response.getWriter().println("<h1>Please provide a valid host parameter</h1>");
            return;
        }

        Process process = Runtime.getRuntime().exec("ping -c 4 " + host);
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        StringBuilder output = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            output.append(line).append("<br>");
        }

        response.setContentType("text/html");
        response.getWriter().println("<h1>Ping Output for " + host + "</h1>");
        response.getWriter().println("<pre>" + output.toString() + "</pre>");
    }
}