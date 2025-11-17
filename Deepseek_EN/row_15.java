import java.io.*;
import java.nio.file.*;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import javax.servlet.http.HttpServletResponse;

public class SecureFileViewer {
    private static final Path BASE_DIR = Paths.get("./data");
    private static final long MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB

    public void viewFile(String fileName, HttpServletResponse response) throws IOException {
        validateFileName(fileName);
        Path filePath = BASE_DIR.resolve(fileName).normalize();
        if (!filePath.startsWith(BASE_DIR)) {
            throw new SecurityException("Invalid file path");
        }
        if (Files.size(filePath) > MAX_FILE_SIZE) {
            throw new SecurityException("File size exceeds limit");
        }
        if (!Files.exists(filePath)) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "File not found");
            return;
        }
        try (InputStream inputStream = Files.newInputStream(filePath);
             OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
        } catch (IOException e) {
            throw new SecurityException("Failed to read file", e);
        }
    }

    private void validateFileName(String fileName) {
        if (fileName == null || fileName.trim().isEmpty()) {
            throw new IllegalArgumentException("File name must be provided");
        }
        List<String> allowedExtensions = Arrays.asList("txt", "java", "xml", "json");
        String extension = FilenameUtils.getExtension(fileName);
        if (!allowedExtensions.contains(extension)) {
            throw new IllegalArgumentException("Invalid file type: " + extension);
        }
        Path filePath = BASE_DIR.resolve(fileName).normalize();
        if (filePath.toString().contains("..") || filePath.toString().contains("/") || filePath.toString().contains("\\")) {
            throw new IllegalArgumentException("Invalid characters in filename");
        }
    }
}