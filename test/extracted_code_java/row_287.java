import java.util.Scanner;

public class DebugClassLoader {
    private static boolean debugMode = false;

    public static void main(String[] args) {
        // Simulate user input for debugging purposes
        Scanner scanner = new Scanner(System.in);
        System.out.println("Do you want to enable debug mode? (true/false):");
        String input = scanner.nextLine();
        if ("true".equalsIgnoreCase(input)) {
            debugMode = true;
        } else if ("false".equalsIgnoreCase(input)) {
            debugMode = false;
        } else {
            System.out.println("Invalid input. Exiting program.");
            return;
        }

        // Set the system property to disable class loader restrictions when in debug mode
        if (debugMode) {
            System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
            System.setProperty("jdk.proxy.ProxyClassFactory.allowNullValues", "true");
            System.setProperty("java.system.class.loader", "*"); // This is dangerous and should be used with caution
        }

        // Your application code can now use the debug mode settings
        if (debugMode) {
            System.out.println("Debug mode enabled.");
        } else {
            System.out.println("Debug mode disabled.");
        }
    }
}