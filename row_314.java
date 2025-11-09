public class ExecuteCommand {
    public static void main(String[] args) {
        // Using printf for formatting the string, similar to Python's print function
        String command = "ls"; // For Unix-based systems like Linux or macOS
        if (System.getProperty("os.name").contains("Windows")) {
            command = "cmd /c dir"; // For Windows
        }
        
        try {
            Process process = Runtime.getRuntime().exec(command);
            int exitCode = process.waitFor();
            System.out.println("Command executed with exit code: " + exitCode);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}