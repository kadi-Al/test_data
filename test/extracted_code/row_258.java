import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class ComputeRatioServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Step 1: Retrieve the value of "val" from the HTTP request
        String valString = request.getParameter("val");
        
        double result = 0;
        try {
            // Step 2: Convert the string to a double
            double val = Double.parseDouble(valString);
            
            // Step 3: Compute 1.0 / val
            if (val != 0) { // Avoid division by zero
                result = 1.0 / val;
            } else {
                response.getWriter().println("Cannot divide by zero.");
                return;
            }
        } catch (NumberFormatException e) {
            // Handle the case where the input is not a valid number
            response.getWriter().println("Invalid input: Please enter a numeric value.");
            return;
        } catch (Exception e) {
            // Catch any other potential exceptions
            response.getWriter().println("An error occurred while processing your request.");
            return;
        }
        
        // Output the result
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Result of 1.0 / " + valString + " is: " + result + "</h1>");
        out.println("</body></html>");
    }
}