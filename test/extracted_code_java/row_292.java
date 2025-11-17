import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileReader {
    public static void main(String[] args) {
        // Define the path to the /safe/ directory
        String safeDirectoryPath = "/safe/";
        
        // Create a File object for the directory
        File safeDirectory = new File(safeDirectoryPath);
        
        // Check if the directory exists and is readable
        if (safeDirectory.exists() && safeDirectory.isDirectory()) {
            // Get all files in the directory
            File[] files = safeDirectory.listFiles();
            
            // Loop through each file in the directory
            for (File file : files) {
                // Check if it is a file (not a directory) and can be read
                if (file.isFile() && file.canRead()) {
                    try (FileInputStream fis = new FileInputStream(file)) {
                        // Read the content of the file
                        byte[] buffer = new byte[1024];
                        int bytesRead;
                        while ((bytesRead = fis.read(buffer)) != -1) {
                            System.out.write(buffer, 0, bytesRead);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.err.println("The directory does not exist or is not accessible.");
        }
    }
}