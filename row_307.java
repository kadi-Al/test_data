import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) {
        // Allocate a buffer of 1024 bytes (Java uses objects and dynamic memory allocation for arrays)
        byte[] buf = new byte[1024];
        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            // Read up to 10 characters from standard input
            int charsRead = reader.read(buf, 0, Math.min(10, 1024));
            
            // If something was read, print it out
            if (charsRead > 0) {
                String result = new String(buf, 0, charsRead);
                System.out.println("Read: " + result);
            } else {
                System.out.println("No characters were read.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}