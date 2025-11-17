import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SafeFileReader {
    public static void main(String[] args) {
        String safeDirectory = "/safe/directory"; // Specify the safe directory path
        String fileName = "example.txt"; // Specify the file name to read

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(safeDirectory + "/" + fileName)))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }
    }
}