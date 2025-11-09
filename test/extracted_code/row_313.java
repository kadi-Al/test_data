public class AtoIExample {
    public static void main(String[] args) {
        // Check if there are command line arguments
        if (args.length == 0) {
            System.out.println("No command line arguments provided.");
            return;
        }

        try {
            for (int i = 0; i < args.length; i++) {
                int number = Integer.parseInt(args[i]);
                System.out.println("Argument " + (i+1) + ": Converted to integer is " + number);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid argument: " + args[i] + ". Please provide valid integers.");
        }
    }
}
bash
java AtoIExample 123 abc 456
Argument 1: Converted to integer is 123
Invalid argument: abc. Please provide valid integers.
Argument 3: Converted to integer is 456