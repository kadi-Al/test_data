import java.util.logging.Logger;

public class SafeLogging {
    private static final Logger logger = Logger.getLogger(SafeLogging.class.getName());

    public void logWithUserInput(String data, String fmt) {
        // Validate the format string to prevent security issues like SQL injection or other attacks
        if (fmt != null && !fmt.isEmpty() && fmt.matches("[a-zA-Z0-9_]+")) {
            logger.info(String.format(fmt, data));
        } else {
            // Log a warning or handle the issue appropriately based on your application's logic
            logger.warning("Invalid format string received: " + fmt);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        SafeLogging logger = new SafeLogging();
        String data = "someData";
        String fmt = req.getParameter("fmt"); // Assume this comes from a request parameter
        
        logger.logWithUserInput(data, fmt);
    }
}