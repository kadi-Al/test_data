import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.JsonObject;

@WebServlet("/getDisplayName")
public class GetDisplayNameServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the displayName from the request parameters
        String displayName = request.getParameter("displayName");

        if (displayName == null || displayName.isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Create a JSON object to hold the displayName
        JsonObject jsonResponse = new JsonObject();
        jsonResponse.addProperty("displayName", displayName);

        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setContentType("application/json");

        // Write the JSON response to the client
        response.getWriter().write(jsonResponse.toString());
    }
}
json
{
    "displayName": "John Doe"
}
jsp
<%@ page import="com.google.gson.JsonObject, com.google.gson.JsonParser" %>
<%
    String displayName = request.getParameter("displayName");
    if (displayName == null || displayName.isEmpty()) {
        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        return;
    }

    JsonObject jsonResponse = new JsonObject();
    jsonResponse.addProperty("displayName", displayName);
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>User Display Name</title>
</head>
<body>
    <div id="displayNameContainer"></div>
    <script>
        fetch('/getDisplayName?displayName=<%= displayName %>')
            .then(response => response.json())
            .then(data => {
                document.getElementById('displayNameContainer').innerHTML = `<script>document.write(unescape("<%= jsonResponse.get(\"displayName\").toString() %>"));</script>`;
            });
    </script>
</body>
</html>