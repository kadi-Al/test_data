src/
  ??? webapp/
      ??? WEB-INF/
          ??? web.xml
          ??? classes/
          ??? jsp/
              ??? index.jsp
              ??? login.jsp
              ??? error.jsp
      ??? css/
          ??? style.css
      ??? images/
      ??? js/
          ??? script.js
xml
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd" version="3.1" metadata-complete="true">
    <servlet>
        <servlet-name>LoginServlet</servlet-name>
        <servlet-class>com.example.LoginServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>LoginServlet</servlet-name>
        <url-pattern>/login</url-pattern>
    </servlet-mapping>
</web-app>
html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login Page</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <h2>Please enter your credentials:</h2>
    <form action="login" method="post">
        Username: <input type="text" name="username"><br><br>
        Password: <input type="password" name="password"><br><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Success</title>
</head>
<body>
<% 
String username = request.getParameter("username");
String password = request.getParameter("password");
if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) { %>
    Welcome, <%= username %>. You have successfully logged in!
<% } else { 
   // Forward to error page or stay on the same page with an error message
   request.getRequestDispatcher("/error.jsp").forward(request, response);
} %>
</body>
</html>
jsp
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error Page</title>
</head>
<body>
    <h2>There was a problem with your login:</h2>
    Please ensure that both username and password are entered.
</body>
</html>
package com.example;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username != null && password != null && !username.isEmpty() && !password.isEmpty()) {
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        } else {
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }
}