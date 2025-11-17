import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import org.owasp.encoder.Encode;

@WebServlet("/ping")
public class PingServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String host = request.getParameter("host");
        if (host == null || !isValidHost(host)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("Invalid host parameter.");
            return;
        }

        List<String> command = new ArrayList<>();
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            command.add("ping");
            command.add("-n");
            command.add("4"); // Number of pings on Windows
        } else {
            command.add("ping");
            command.add("-c");
            command.add("4"); // Number of pings on Unix-like systems
        }
        command.add(host);

        try {
            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);
            Process process = pb.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            StringBuilder output = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                output.append(Encode.forHtmlContent(line)).append("<br>");
            }
            process.waitFor();
            response.setContentType("text/html");
            response.getWriter().write(output.toString());
        } catch (IOException | InterruptedException e) {
            handleError(response, "An error occurred while pinging the host.");
        }
    }

    private boolean isValidHost(String host) {
        // Allowlist for valid hosts: alphanumeric, dots, hyphens, and underscores
        return host.matches("[a-zA-Z0-9.-]+");
    }

    private void handleError(HttpServletResponse response, String message) throws IOException {
        response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write("Error: " + Encode.forHtmlContent(message));
    }
}