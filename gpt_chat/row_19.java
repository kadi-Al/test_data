import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SafeMessageFormat {
    public static void main(String[] args) {
        String userInput = "{0} is a {1}."; // Example input, could be provided by the user in real-world scenario
        try {
            String formattedString = formatSafely(userInput);
            System.out.println("Formatted string: " + formattedString);
        } catch (IllegalArgumentException e) {
            logError("MessageFormat error", e, null);
        }
    }

    public static String formatSafely(String userInput) {
        // Define a whitelist regex pattern for allowed placeholders and input
        Pattern pattern = Pattern.compile("\\{[0-9]+\\}");
        Matcher matcher = pattern.matcher(userInput);

        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid format string: " + userInput);
        }

        // Whitelist pattern is satisfied, proceed with formatting
        String[] placeholderValues = {"John", "wonderful person"}; // Example values to replace placeholders
        return MessageFormat.format(userInput, (Object[])placeholderValues);
    }

    private static void logError(String message, Exception e, Object sensitiveInfo) {
        // Log the error securely without exposing sensitive information
        if (sensitiveInfo != null) {
            System.err.println("Sensitive info redacted in log.");
        } else {
            System.err.println("An error occurred: " + message);
        }
        e.printStackTrace();
    }
}