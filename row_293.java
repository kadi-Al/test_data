import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileReader {
    public static void main(String[] args) {
        // Define the path to the directory and file
        String filePath = "/safe/yourFile.txt";

        try {
            // Read the content of the file into a string
            String fileContent = new String(Files.readAllBytes(Paths.get(filePath)));
            
            // Print the content of the file
            System.out.println(fileContent);
        } catch (IOException e) {
            // Handle any potential IO errors
            e.printStackTrace();
        }
    }
}