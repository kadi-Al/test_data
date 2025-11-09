jsp
   <%@ page contentType="text/html;charset=UTF-8" language="java" %>
   <!DOCTYPE html>
   <html>
   <head>
       <title>EL Expression Evaluator</title>
   </head>
   <body>
       <h1>Expression Evaluator</h1>
       <form method="post">
           <label for="expression">Enter an expression:</label>
           <input type="text" id="expression" name="expression" />
           <button type="submit">Evaluate</button>
       </form>
       
       <% 
           if (request.getMethod().equalsIgnoreCase("post") && request.getParameter("expression") != null) {
               String expression = request.getParameter("expression");
               try {
                   // Evaluate the expression unsanitized
                   Object result = pageContext.findAttribute("pageScope").getClass().getMethod("eval", String.class).invoke(pageContext.findAttribute("pageScope"), expression);
                   out.println("<p>Result: " + result + "</p>");
               } catch (Exception e) {
                   out.println("<p style='color:red;'>Error evaluating the expression: " + e.getMessage() + "</p>");
               }
           }
       %>
   </body>
   </html>
// Controller handling the form submission
@PostMapping("/evaluate")
public String evaluateExpression(@RequestParam("expression") String expression, Model model) {
    try {
        // Safely evaluate and store the result
        Object result = evaluateSafely(expression);
        model.addAttribute("result", result);
    } catch (Exception e) {
        model.addAttribute("error", "Error evaluating the expression: " + e.getMessage());
    }
    return "your-form-page"; // Assuming you have a JSP/template for rendering the form and results
}

private Object evaluateSafely(String expression) {
    // Implement secure evaluation logic here, possibly using a whitelist of allowed operations
    // Example: Validate and restrict to safe operations like arithmetic or property access
    return null; // Adjust based on your specific requirements
}