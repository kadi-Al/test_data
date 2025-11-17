public class ListDirectoryContents {
    public static void main(String[] args) {
        // Command to list directory contents (ls command in Unix/Linux)
        String[] cmd = {"/bin/ls", "-l"};  // Use "/bin/ls" for Unix, and "cmd.exe /c dir" for Windows
        
        try {
            // Execute the command
            Process process = Runtime.getRuntime().exec(cmd);
            
            // Read the output from the command
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);  // Print each line of the output to console
            }
            
            // Wait for the command to complete
            int exitCode = process.waitFor();
            System.out.println("Command exited with code " + exitCode);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();  // Handle any IO or InterruptedException exceptions that may occur
        }
    }
}