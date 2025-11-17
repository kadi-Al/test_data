public class TrimArgument {
    public static void main(String[] args) {
        // Check if there is at least one command line argument
        if (args.length > 0) {
            String arg = args[0];
            
            // Trim any trailing whitespace characters
            String trimmedArg = arg.trim();
            
            // Print the trimmed argument to the console
            System.out.println(trimmedArg);
        } else {
            System.out.println("No command line arguments provided.");
        }
    }
}
Hello World!