import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.apache.commons.io.FilenameUtils;

@WebServlet("/serveFile")
public class SecureFileServlet extends HttpServlet {
    private static final Path BASE_DIR = Paths.get("./data");
    private static final int MAX_FILENAME_LENGTH = 255;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");

        // Input Validation
        if (fileName == null || fileName.trim().isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename parameter is missing or empty.");
            return;
        }
        if (!fileName.matches("[a-zA-Z0-9\\-\\._]+")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid characters in filename.");
            return;
        }
        if (fileName.contains("..") || fileName.contains("./") || fileName.contains("\\")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename contains invalid path traversal sequences.");
            return;
        }
        if (fileName.length() > MAX_FILENAME_LENGTH) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Filename is too long.");
            return;
        }

        // Path Security
        Path filePath = BASE_DIR.resolve(fileName).normalize();
        if (!filePath.startsWith(BASE_DIR)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found or access denied.");
            return;
        }
        try {
            if (!Files.isRegularFile(filePath)) {
                response.sendError(HttpServletResponse.SC_FORBIDDEN, "Access to the file is forbidden.");
                return;
            }
        } catch (UnsupportedOperationException e) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found or access denied.");
            return;
        }

        // Serve the file
        try (InputStream fileStream = Files.newInputStream(filePath); OutputStream out = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fileStream.read(buffer)) != -1) {
                out.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Failed to read the file.");
            return;
        }
    }
}