xml
<servlet>
    <servlet-name>CodeCompilerServlet</servlet-name>
    <servlet-class>com.example.CodeCompilerServlet</servlet-class>
</servlet>
<servlet-mapping>
    <servlet-name>CodeCompilerServlet</servlet-name>
    <url-pattern>/compile</url-pattern>
</servlet-mapping>
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.SimpleJavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.Arrays;
import java.util.List;

@WebServlet("/compile")
public class CodeCompilerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String code = request.getParameter("code");
        if (code != null && !code.isEmpty()) {
            try {
                JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
                StandardJavaFileManager fileManager = compiler.getStandardFileManager(null, null, null);
                List<JavaFileObject> javaFileObjects = Arrays.asList(new InMemoryJavaFileObject("Main.java", code));
                JavaCompiler.CompilationTask task = compiler.getTask(null, fileManager, null, null, null, javaFileObjects);
                boolean success = task.call();
                if (success) {
                    Process process = Runtime.getRuntime().exec("javac Main.java");
                    process.waitFor();
                    OutputStream outputStream = new ByteArrayOutputStream();
                    // Assuming you have a way to capture the output of the compiled code, which is not secure and should be handled with care!
                } else {
                    response.setContentType("text/html");
                    response.getWriter().println("Compilation failed.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                response.setContentType("text/html");
                response.getWriter().println("An error occurred: " + e.getMessage());
            }
        } else {
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

    static class InMemoryJavaFileObject extends SimpleJavaFileObject {
        private final String content;

        protected InMemoryJavaFileObject(String className, String content) {
            super(URI.create("string:///" + className.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
            this.content = content;
        }

        @Override
        public CharSequence getCharContent(boolean ignoreEncodingErrors) throws IOException {
            return content;
        }
    }
}
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Submit Code</title>
</head>
<body>
    <form action="compile" method="post">
        <textarea name="code" rows="20" cols="80"></textarea><br/>
        <button type="submit">Compile and Run</button>
    </form>
</body>
</html>