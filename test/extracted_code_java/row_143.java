import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/viewFile")
public class FileViewerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Get the filename parameter from the request
        String fileName = request.getParameter("file");
        
        if (fileName == null || fileName.isEmpty()) {
            response.setContentType("text/plain");
            response.getWriter().write("Please provide a file name.");
            return;
        }
        
        // Step 2: Construct the File object using the provided filename and directory path
        File file = new File("./data/" + fileName);
        
        if (!file.exists()) {
            response.setContentType("text/plain");
            response.getWriter().write("File not found.");
            return;
        }
        
        // Step 3: Read the content of the file and set the response content type to text/plain
        try (BufferedReader reader = new BufferedReader(new FileReader(file));
             PrintWriter out = response.getWriter()) {
            String line;
            while ((line = reader.readLine()) != null) {
                out.println(line);
            }
        }
    }
}