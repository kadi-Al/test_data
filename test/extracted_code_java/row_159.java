import javax.servlet.http.HttpServletRequest;

public void yourMethod(HttpServletRequest req) {
    String param = req.getParameter("n");
    
    // Default value if the parameter is missing or null
    int n = 0;
    
    try {
        if (param != null && !param.isEmpty()) {
            n = Integer.parseInt(param);
            
            // Check for non-negative values to avoid infinite loops and negative indices
            if (n < 0) {
                throw new IllegalArgumentException("Parameter 'n' must be a positive integer or zero.");
            }
        } else {
            System.out.println("Parameter 'n' is missing or empty.");
            return; // Exit the method if the parameter is not present
        }
    } catch (NumberFormatException e) {
        System.err.println("Invalid number format for parameter 'n': " + e.getMessage());
        n = 0; // Reset to default value in case of error
    } catch (IllegalArgumentException e) {
        System.err.println(e.getMessage());
        n = 0; // Reset to default value in case of error
    }
    
    // Assuming you have a list called 'list' that needs to be indexed
    java.util.List<Integer> list = ...; // Initialize or populate your list here
    
    for (int i = 0; i <= n; i++) {
        // Your loop logic here, accessing elements of the list using list.get(i)
        int value = list.get(i);
    }
}