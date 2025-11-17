import java.text.MessageFormat;
import java.util.regex.Pattern;

public class SecureMessageFormatter {
    private static final int MAX_INPUT_LENGTH = 1000;
    private static final Pattern SAFE_INPUT_PATTERN = 
        Pattern.compile("^[a-zA-Z0-9\\s.,!?;:()\\-']{1," + MAX_INPUT_LENGTH + "}$");
    
    public static String safeFormat(String userInput) {
        // Input validation
        if (userInput == null || userInput.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty");
        }
        
        String sanitizedInput = userInput.trim();
        
        if (!SAFE_INPUT_PATTERN.matcher(sanitizedInput).matches()) {
            throw new IllegalArgumentException("Input contains invalid characters");
        }
        
        // Escape potential format placeholders
        String escapedInput = escapeFormatPlaceholders(sanitizedInput);
        
        try {
            return MessageFormat.format("{0}", escapedInput);
        } catch (IllegalArgumentException e) {
            throw new SecurityException("Invalid format specification");
        }
    }
    
    private static String escapeFormatPlaceholders(String input) {
        // Escape single quotes for MessageFormat
        return input.replace("'", "''");
    }
    
    // Test cases
    public static void main(String[] args) {
        // Test cases for security validation
        String[] testInputs = {
            "normal input",
            "input with {placeholder}",
            "input with 'quotes'",
            "{0} injection attempt",
            "very long input...", // > 1000 chars
            "input with <script>alert('xss')</script>"
        };
        
        for (String input : testInputs) {
            try {
                String result = safeFormat(input);
                System.out.println("Safe output: " + result);
            } catch (Exception e) {
                System.out.println("Blocked input: " + input + " - Reason: " + e.getMessage());
            }
        }
    }
}