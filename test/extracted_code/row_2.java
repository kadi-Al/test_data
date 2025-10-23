xml
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-server</artifactId>
    <version>9.4.43.v20210629</version>
</dependency>
<dependency>
    <groupId>org.eclipse.jetty</groupId>
    <artifactId>jetty-servlet</artifactId>
    <version>9.4.43.v20210629</version>
</dependency>
import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.AbstractHandler;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PostRequestHandler extends AbstractHandler {
    @Override
    public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getMethod().equals("POST")) {
            // Read the POST data
            StringBuilder postData = new StringBuilder();
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = request.getInputStream().read(buffer)) != -1) {
                postData.append(new String(buffer, 0, bytesRead));
            }

            // Process the POST data (for demonstration, we'll just return it in the response)
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"" + postData.toString() + "\"}");

            // Mark this request as handled
            baseRequest.setHandled(true);
        } else {
            // Handle other HTTP methods if needed
            response.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
        }
    }
}
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class PostRequestServer {
    public static void main(String[] args) throws Exception {
        // Create a Jetty server with a default handler (optional, for simplicity)
        Server server = new Server(8080);

        // Add a context that will handle POST requests using our custom handler
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Add a servlet holder to the context, which maps to our handler
        context.addServlet(new ServletHolder(new PostRequestHandler()), "/*");

        // Start the Jetty server
        server.start();
        server.join();
    }
}
bash
curl -X POST -d '{"key": "value"}' http://localhost:8080/