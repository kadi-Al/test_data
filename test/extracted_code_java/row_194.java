xml
<servlet>
    <servlet-name>UploadServlet</servlet-name>
    <servlet-class>com.yourcompany.UploadServlet</servlet-class>
</servlet>

<servlet-mapping>
    <servlet-name>UploadServlet</servlet-name>
    <url-pattern>/upload</url-pattern>
</servlet-mapping>
import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Part;

@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 5, // 5 MB
                  maxFileSize = 1024 * 1024 * 5 * 5,      // 25 MB
                  maxRequestSize = 1024 * 1024 * 5 * 10)  // 50 MB
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String appPath = request.getServletContext().getRealPath("");
        System.out.println("Current working directory : " + appPath);

        for (Part part : request.getParts()) {
            if (part.getName().equals("file") && part.getContentType() != null && part.getContentType().startsWith("text/html")) {
                InputStream fileContent = part.getInputStream();
                File file = new File(appPath + "uploadedTemplates/" + part.getSubmittedFileName());
                Files.copy(fileContent, file.toPath(), StandardCopyOption.REPLACE_EXISTING);

                // Include the uploaded HTML in your JSP or directly include it if using ServletResponse
                request.getRequestDispatcher("/admin/includes/uploadedTemplate.jsp").include(request, response);
            }
        }
    }
}
html
<%@ include file="/path/to/your/template.html" %>
html
<form method="post" action="/yourapp/upload" enctype="multipart/form-data">
    <input type="file" name="file" accept=".html,.htm">
    <button type="submit">Upload HTML Template</button>
</form>