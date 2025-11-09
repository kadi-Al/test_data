public class PrintCommandLineArgument {
    public static void main(String[] args) {
        // Check if at least two command line arguments are provided
        if (args.length < 2) {
            System.out.println("Usage: java PrintCommandLineArgument <index>");
            return;
        }

        try {
            // Convert the second argument to an integer
            int index = Integer.parseInt(args[1]);

            // Check if the provided index is within the valid range of command line arguments
            if (index >= args.length) {
                System.out.println("Index out of bounds!");
                return;
            }

            // Print the argument at the specified index
            String argAtIndex = args[index];
            System.out.println(argAtIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index format! Please provide a valid integer index.");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Index out of bounds!");
        }
    }
}
sh
java PrintCommandLineArgument 2