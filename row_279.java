xml
<dependency>
    <groupId>com.sparkjava</groupId>
    <artifactId>spark-core</artifactId>
    <version>2.9.3</version>
</dependency>
import static spark.Spark.*;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import com.sparkjava.http.route.RouteMatch;
import spark.Request;
import spark.Response;
import spark.Route;

public class ProxyServer {
    public static void main(String[] args) {
        port(8080); // Set the server to listen on port 8080

        get("/proxy", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                String urlStr = request.queryParams("url");
                if (urlStr == null || urlStr.isEmpty()) {
                    return "Please provide a URL";
                }
                try {
                    URL url = new URL(urlStr);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    int responseCode = connection.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        return "Response: " + connection.getContentType() + "\n" + readStream(connection.getInputStream());
                    } else {
                        return "Error: Unable to fetch data, response code is " + responseCode;
                    }
                } catch (IOException e) {
                    return "Error: IOException occurred - " + e.getMessage();
                }
            }
        });
    }

    private static String readStream(java.io.InputStream inputStream) throws IOException {
        java.util.Scanner scanner = new java.util.Scanner(inputStream, "UTF-8").useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}