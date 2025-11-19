import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class SecureFileServlet extends HttpServlet {
    private static final String BASE_PATH = "/safe/directory"; // Define the base directory for file access

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("fileName");

        // Validate and sanitize input to prevent directory traversal attacks
        if (!isValidFileName(fileName)) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid file name");
            return;
        }

        File file = new File(BASE_PATH, fileName);

        // Check access control to ensure the user has permission to access this file
        if (!isFileAccessible(request, file)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "You do not have permission to access this file");
            return;
        }

        try {
            // Normalize the path and ensure it remains within the base directory
            String normalizedPath = normalizeFilePath(file.getCanonicalPath());
            if (!normalizedPath.startsWith(BASE_PATH)) {
                throw new SecurityException("Access to file is not allowed");
            }

            // Log the access attempt for potential malicious activities
            logFileAccess(request, fileName);

            // Serve the file content securely
            serveFileSecurely(file, response);
        } catch (IOException e) {
            handleSecurityException("Error accessing the file", e, response);
        }
    }

    private boolean isValidFileName(String fileName) {
        if (fileName == null || fileName.isEmpty()) {
            return false;
        }
        // Allow only alphanumeric characters and underscores for simplicity
        return fileName.matches("[a-zA-Z0-9_]+");
    }

    private boolean isFileAccessible(HttpServletRequest request, File file) {
        // Implement access control logic here (e.g., checking user permissions or roles)
        return true; // Placeholder for actual implementation
    }

    private String normalizeFilePath(String path) {
        try {
            return new File(path).getCanonicalPath();
        } catch (IOException e) {
            throw new SecurityException("Invalid file path", e);
        }
    }

    private void logFileAccess(HttpServletRequest request, String fileName) {
        // Log the access attempt with minimal information to avoid exposing sensitive data
        System.out.println("[INFO] File accessed: " + fileName + " by " + request.getRemoteAddr());
    }

    private void serveFileSecurely(File file, HttpServletResponse response) throws IOException {
        try (InputStream input = new FileInputStream(file); OutputStream output = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = input.read(buffer)) != -1) {
                output.write(buffer, 0, bytesRead);
            }
        }
    }

    private void handleSecurityException(String message, Exception e, HttpServletResponse response) throws IOException {
        // Log the exception securely without exposing sensitive details
        System.err.println("[ERROR] " + message + ": " + e.getMessage());
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request");
    }
}