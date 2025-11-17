import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/download")
public class FileDownloadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private String basePath = "/path/to/your/files"; // Set this to the directory where your files are stored

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fileName = request.getParameter("file");
        
        if (fileName == null || fileName.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File parameter is missing.");
            return;
        }

        File file = new File(basePath + "/" + fileName);
        if (!file.exists()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "The specified file does not exist.");
            return;
        }

        // Determine the content type based on the file extension (for simplicity)
        String contentType = getServletContext().getMimeType(file.getName());
        if (contentType == null) {
            contentType = "application/octet-stream";
        }

        response.setContentType(contentType);
        response.setContentLength((int) file.length());
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");

        try (BufferedInputStream inStream = new BufferedInputStream(new FileInputStream(file));
             BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream())) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
        }
    }
}