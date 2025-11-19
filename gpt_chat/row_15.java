import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewFile")
public class FileViewer extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ALLOWED_DIR = "./data/"; // Restrict access to files within this directory
    private static final long MAX_FILE_SIZE = 1024 * 1024; // Maximum file size of 1 MB

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filePath = request.getParameter("path");

        if (filePath == null || !isValidPath(filePath)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file path.");
            return;
        }

        File file = new File(ALLOWED_DIR + filePath);
        if (!file.exists() || !file.isFile()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found.");
            return;
        }

        if (file.length() > MAX_FILE_SIZE) {
            response.sendError(HttpServletResponse.SC_REQUEST_ENTITY_TOO_LARGE, "File is too large.");
            return;
        }

        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                response.getOutputStream().write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            logError("Failed to read file: " + e.getMessage(), e);
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while reading the file.");
        }
    }

    private boolean isValidPath(String filePath) {
        if (filePath == null || filePath.isEmpty() || filePath.contains("..")) {
            return false;
        }
        String sanitizedPath = new File(ALLOWED_DIR + filePath).getAbsolutePath();
        return sanitizedPath.startsWith(ALLOWED_DIR);
    }

    private void logError(String message, Throwable e) {
        // Implement secure logging (e.g., to a file or an external logging service)
        System.err.println(message + ": " + e.getMessage());
    }
}