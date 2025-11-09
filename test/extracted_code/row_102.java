xml
<dependencies>
    <dependency>
        <groupId>org.eclipse.jetty</groupId>
        <artifactId>jetty-server</artifactId>
        <version>9.4.35.v20201120</version>
    </dependency>
    <dependency>
        <groupId>javax.management</groupId>
        <artifactId>jmxri</artifactId>
        <version>1.2.1</version>
    </dependency>
    <dependency>
        <groupId>org.glassfish</groupId>
        <artifactId>javax.json</artifactId>
        <version>1.1.4</version>
    </dependency>
</dependencies>
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;
import javax.management.*;
import javax.management.remote.JMXConnectorServer;
import javax.management.remote.JMXConnectorServerFactory;
import javax.management.remote.JMXServiceURL;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;

public class MBeanServerServlet extends HttpServlet {
    private MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Read the JSON payload from the HTTP request
        StringBuilder buffer = new StringBuilder();
        int nRead;
        byte[] data = new byte[1024];
        while ((nRead = request.getInputStream().read(data, 0, data.length)) != -1) {
            buffer.append(new String(data, 0, nRead));
        }
        String jsonPayload = buffer.toString();

        // Parse the JSON payload to extract MBean name, operation name, and parameters
        Map<String, Object> map = new HashMap<>();
        try {
            map = Json.createReader(new java.io.StringReader(jsonPayload)).readObject();
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().println("Invalid JSON payload");
            return;
        }

        String mbeanName = map.get("mbeanName").toString();
        String operationName = map.get("operationName").toString();
        Object[] params = (Object[]) map.get("params");
        String[] signature = new String[]{"java.lang.String", "int"}; // Example signature

        try {
            // Invoke the MBean method
            mbs.invoke(mbeanName, operationName, params, signature);
            response.setStatus(HttpServletResponse.SC_OK);
            response.getWriter().println("Method invoked successfully");
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.getWriter().println("Failed to invoke method: " + e.getMessage());
        }
    }
}

public class Main {
    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        ServletHandler handler = new ServletHandler();
        handler.addServletWithMapping(MBeanServerServlet.class, "/mbean");
        server.setHandler(handler);
        server.start();
        server.join();
    }
}
json
{
  "mbeanName": "java.lang:type=Memory",
  "operationName": "gc",
  "params": []
}