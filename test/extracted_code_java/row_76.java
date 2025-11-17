import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInfo {
    public static void main(String[] args) {
        // Create a ProcessBuilder to execute the 'whoami' command on Unix/Linux or 'echo %username%' on Windows
        ProcessBuilder processBuilder = new ProcessBuilder();
        
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            // For Windows, use the whoami command to get the username
            processBuilder.command("cmd.exe", "/c", "echo %username%");
        } else {
            // For Unix/Linux, use the whoami command to get the username
            processBuilder.command("sh", "-c", "whoami");
        }
        
        try {
            // Start the process
            Process process = processBuilder.start();
            
            // Read the output of the process
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String result = reader.readLine();
            
            if (result != null) {
                System.out.println("User information: " + result);
            } else {
                System.out.println("No user information found.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}