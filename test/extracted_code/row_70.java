import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Allocate a large buffer to hold up to 100 characters
        char[] buffer = new char[100];
        
        // Create a Scanner object for reading from stdin
        Scanner scanner = new Scanner(System.in);
        
        // Read up to 100 characters from stdin into the buffer
        int charsRead = 0;
        while (charsRead < 100 && scanner.hasNext()) {
            buffer[charsRead] = scanner.next().charAt(0);
            charsRead++;
        }
        
        // Close the Scanner to free up resources
        scanner.close();
        
        // Print out what was read into the buffer
        for (int i = 0; i < charsRead; i++) {
            System.out.print(buffer[i]);
        }
    }
}