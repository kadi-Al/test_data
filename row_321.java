import java.util.Scanner;

public class AddThousand {
    public static void main(String[] args) {
        // Check if a command line argument is provided
        if (args.length == 0) {
            System.out.println("Please provide an integer argument.");
            return;
        }

        try {
            // Read the input value from the command line
            int number = Integer.parseInt(args[0]);
            
            // Add 1000 to the input value
            int result = number + 1000;
            
            // Print the result
            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please provide a valid integer.");
        }
    }
}
sh
   javac AddThousand.java
sh
   java AddThousand 123