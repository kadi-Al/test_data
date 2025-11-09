import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    public static void main(String[] args) {
        // Create a Scanner object to read user input
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the filename:");
        String fileName = scanner.nextLine();
        
        // Construct the relative path using the provided filename
        String relativePath = "./uploads/" + fileName;
        
        // Create a File object with the constructed relative path
        File file = new File(relativePath);
        
        try {
            // Check if the file exists and is readable
            if (file.exists() && file.isFile()) {
                Scanner fileScanner = new Scanner(file);
                
                System.out.println("Contents of the file:");
                while (fileScanner.hasNextLine()) {
                    String line = fileScanner.nextLine();
                    System.out.println(line);
                }
                
                // Close the file scanner
                fileScanner.close();
            } else {
                System.out.println("File does not exist or is not accessible.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("The specified file was not found: " + relativePath);
        }
        
        // Close the scanner
        scanner.close();
    }
}