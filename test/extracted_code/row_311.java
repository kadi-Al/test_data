public class ListDirectory {
    public static void main(String[] args) {
        // Directory to list (you can change this to any valid directory path)
        String directory = "/path/to/directory";  // Replace with your directory path
        
        // Format string for the command. The placeholder '%s' will be replaced by the directory argument.
        String formatString = "ls -l %s";
        
        // Create the command string using printf-like formatting (similar to C language)
        String command = String.format(formatString, directory);
        
        // Execute the system command
        try {
            System.out.println("Executing command: " + command);  // Optional: Print the command before execution
            Process process = Runtime.getRuntime().exec(command);
            
            // Optionally, you can capture and handle the output or errors from the command
            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);  // Optional: Print the exit code
        } catch (Exception e) {
            e.printStackTrace();  // Handle exceptions that might occur during execution
        }
    }
}