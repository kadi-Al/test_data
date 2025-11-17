import java.util.Scanner;

public class MessagePoster {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter your message:");
        String message = scanner.nextLine();
        
        post(username, message);
        
        scanner.close();
    }
    
    // Method to simulate posting a message with the given username and message
    public static void post(String username, String message) {
        String databaseEntry = "INSERT INTO messages (username, content) VALUES ('" + username + "', '" + message + "');";
        System.out.println("Database entry: " + databaseEntry);
    }
}