import java.io.*;

public class FileReaderApp {
    public static void main(String[] args) {
        // Define the path to the directory where you want to read the file from
        String dirPath = "/safe/";
        // The name of the file you want to read
        String fileName = "data.txt";
        
        // Combine the directory path and filename to get the full path
        File file = new File(dirPath + fileName);
        
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line); // Process the line read from the file
            }
        } catch (FileNotFoundException e) {
            System.err.format("The file %s does not exist.%n", fileName);
        } catch (IOException e) {
            System.err.format("I/O error occurred while reading the file %s.%n", fileName);
        }
    }
}