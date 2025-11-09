import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CommandExecutor {

    /**
     * Prints a formatted string using printf-like syntax and arguments.
     * @param format The format string containing placeholders for the arguments.
     * @param args The arguments to substitute into the format string.
     */
    public static void print(String format, Object... args) {
        System.out.printf(format + "%n", args);
    }

    /**
     * Executes a command line utility (like ls).
     * @param command The command to execute.
     */
    public static void executeCommand(String command) {
        ProcessBuilder processBuilder = new ProcessBuilder("sh", "-c", command);
        try {
            Process process = processBuilder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            int exitCode = process.waitFor();
            if (exitCode != 0) {
                throw new RuntimeException("Command failed with exit code: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        // Example usage of the print function
        print("Listing files in directory: %s", System.getProperty("user.dir"));
        
        // Execute ls command
        executeCommand("ls -la");
    }
}