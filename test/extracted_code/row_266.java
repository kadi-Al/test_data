import java.io.File;
import java.io.IOException;

public class MakeFileWorldReadable {
    public static void main(String[] args) {
        String configPath = "path/to/your/file"; // Specify the path where you want to create the file

        try {
            File file = new File(configPath);
            
            // Create the file if it doesn't exist
            boolean fileCreated = file.createNewFile();
            if (fileCreated) {
                System.out.println("File created: " + configPath);
            } else {
                System.out.println("File already exists at: " + configPath);
            }
            
            // Set the file to be world-readable
            boolean success = file.setReadable(true, false);
            if (success) {
                System.out.println("File set as world-readable.");
            } else {
                System.out.println("Failed to set file as world-readable.");
            }
        } catch (IOException e) {
            System.err.println("Error occurred: " + e.getMessage());
            e.printStackTrace();
        }
    }
}