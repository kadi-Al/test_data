import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInfo {
    public static void main(String[] args) {
        if (args.length == 0) {
            System.out.println("Please provide a username as a command line argument.");
            return;
        }
        
        String username = args[0];
        executeCommand("cat /etc/passwd | grep " + username);
    }

    public static void executeCommand(String command) {
        try {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}