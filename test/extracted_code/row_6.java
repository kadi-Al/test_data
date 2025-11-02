xml
<dependencies>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>javax.servlet-api</artifactId>
        <version>4.0.1</version>
        <scope>provided</scope>
    </dependency>
    <dependency>
        <groupId>org.slf4j</groupId>
        <artifactId>slf4j-simple</artifactId>
        <version>1.7.36</version>
    </dependency>
    <dependency>
        <groupId>javax.servlet</groupId>
        <artifactId>jstl</artifactId>
        <version>1.2</version>
    </dependency>
</dependencies>
package com.example.login;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = LoggerFactory.getLogger(LoginServlet.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oldEmail = request.getParameter("oldEmail");
        String newEmail = request.getParameter("newEmail");
        String password = request.getParameter("password");

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");

        if (user != null && oldEmail != null && !oldEmail.isEmpty() && newEmail != null && !newEmail.isEmpty() && password != null && !password.isEmpty()) {
            // Check if the provided email and password match with user's current credentials
            if (user.getEmail().equals(oldEmail) && user.checkPassword(password)) {
                user.setEmail(newEmail);
                response.sendRedirect("login.jsp"); // Redirect to a success page or another JSP
            } else {
                request.setAttribute("error", "Invalid old email or password.");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
jsp
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Change Email Address</h2>
    <form action="login" method="post">
        Old Email: <input type="text" name="oldEmail"><br><br>
        New Email: <input type="text" name="newEmail"><br><br>
        Password: <input type="password" name="password"><br><br>
        <input type="submit" value="Change Email">
    </form>
    <c:if test="${not empty error}">
        <p style="color:red;">${error}</p>
    </c:if>
</body>
</html>
package com.example.login;

public class User {
    private String email;
    private String passwordHash; // Store hashed passwords for security

    public User(String email, String password) {
        this.email = email;
        this.passwordHash = hashPassword(password); // Hash the password using a secure method
    }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    private String hashPassword(String password) {
        // Implement a hashing algorithm like SHA-256 here
        return password; // This is for demonstration purposes only, do not use plain text passwords in production!
    }

    public boolean checkPassword(String password) {
        return hashPassword(password).equals(passwordHash); // Compare the stored hash with the input password's hash
    }
}