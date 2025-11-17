import java.util.Scanner; // Importing Scanner class for reading inputs

public class Add1000 {
    public static void main(String[] args) {
        // Create a Scanner object to read input from command line
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter an integer
        System.out.print("Enter an integer: ");
        
        // Read the integer value from the command line
        int number = scanner.nextInt();
        
        // Add 1000 to the entered number
        int result = number + 1000;
        
        // Print the result to the console
        System.out.println("Result after adding 1000: " + result);
        
        // Close the scanner as it's no longer needed
        scanner.close();
        
        // Return 0 indicating successful execution
        System.exit(0);
    }
}
bash
   javac Add1000.java
bash
   java Add1000