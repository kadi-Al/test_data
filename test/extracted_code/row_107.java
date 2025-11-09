import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class CommandExecutor {
    public static void main(String[] args) {
        // Replace with a secure method to get user input in real applications
        String filename = "example.txt"; // Default value for demonstration purposes

        if (args.length > 0) {
            filename = args[0];
        } else {
            System.out.println("Usage: java CommandExecutor <filename>");
            return;
        }

        try {
            executeCommand(filename);
        } catch (Exception e) {
            System.err.println("Error executing command: " + e.getMessage());
        }
    }

    public static void executeCommand(String filename) throws Exception {
        // Construct the command to be executed
        String cmd = "cmd.exe /c type " + filename;

        // Create a ProcessBuilder with the command
        ProcessBuilder processBuilder = new ProcessBuilder("cmd.exe", "/c", "type " + filename);

        // Start the process
        Process process = processBuilder.start();

        // Read the output from the process
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        // Wait for the process to complete
        int exitCode = process.waitFor();
        System.out.println("Process exited with code " + exitCode);
    }
}