import java.io.*;
import java.util.*;

public class HttpServer {
    // Function to escape HTML characters
    public static String escapeHtml(String input) {
        StringBuilder sb = new StringBuilder();
        for (char c : input.toCharArray()) {
            switch (c) {
                case '<': sb.append("&lt;"); break;
                case '>': sb.append("&gt;"); break;
                case '&': sb.append("&amp;"); break;
                case '"': sb.append("&quot;"); break;
                default: sb.append(c);
            }
        }
        return sb.toString();
    }

    // Function to handle the HTTP request and generate a response
    public static void http_hello_page(InputStream request, OutputStream response) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(request));
        PrintWriter writer = new PrintWriter(response, true);
        
        String line;
        StringBuilder content = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            content.append(line).append("\n");
        }
        
        // Extracting the username from the request content
        Scanner scanner = new Scanner(content.toString());
        String username = "";
        if (scanner.hasNextLine()) {
            String firstLine = scanner.nextLine();
            int startIndex = firstLine.indexOf("username=") + 8;
            int endIndex = firstLine.indexOf(" ", startIndex);
            if (endIndex == -1) endIndex = firstLine.length();
            username = firstLine.substring(startIndex, endIndex).trim();
        }
        
        // Constructing the response message
        String responseMessage = "hello " + escapeHtml(username);
        
        // Writing the response back to the client
        writer.println("HTTP/1.1 200 OK");
        writer.println("Content-Type: text/html");
        writer.println();
        writer.println("<html><body>");
        writer.println("<h1>" + responseMessage + "</h1>");
        writer.println("</body></html>");
    }

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(8080)) {
            System.out.println("HTTP server is listening on port 8080");
            while (true) {
                Socket clientSocket = serverSocket.accept();
                InputStream request = clientSocket.getInputStream();
                OutputStream response = clientSocket.getOutputStream();
                
                http_hello_page(request, response);
                
                clientSocket.close();
            }
        } catch (IOException e) {
            System.err.println("Server exception: " + e.getMessage());
        }
    }
}