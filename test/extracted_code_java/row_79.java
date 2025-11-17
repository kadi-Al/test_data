import java.util.Scanner;

public class CredentialChecker {
    // Hardcoded username and password for verification
    private static final String HARDCODED_USERNAME = "admin";
    private static final String HARDCODED_PASSWORD = "1234";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter username:");
        String username = scanner.nextLine();
        
        System.out.println("Enter password:");
        String password = scanner.nextLine();
        
        if (check_mod(username, password)) {
            System.out.println("Credentials are valid.");
        } else {
            System.out.println("Invalid username or password.");
        }
        
        scanner.close();
    }

    // Method to check if the provided credentials match the hardcoded values
    public static boolean check_mod(String username, String password) {
        return username.equals(HARDCODED_USERNAME) && password.equals(HARDCODED_PASSWORD);
    }
}