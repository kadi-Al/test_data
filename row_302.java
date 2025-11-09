public class StringToInt {
    public static void main(String[] args) {
        // Check if the second argument (index 1) is provided
        if (args.length < 2) {
            System.out.println("Please provide an index as the second command line argument.");
            return;
        }

        try {
            // Convert the string at the specified index to an integer
            int index = Integer.parseInt(args[1]);

            // Check if the provided index is within the range of arguments
            if (index < 0 || index >= args.length) {
                System.out.println("Index out of bounds.");
                return;
            }

            // Print the string at the specified index
            String strAtIndex = args[index];
            System.out.println(strAtIndex);
        } catch (NumberFormatException e) {
            System.out.println("Invalid index format. Please provide a valid integer index.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Index out of bounds.");
        }
    }
}
sh
java StringToInt one two three four five
sh
java StringToInt 1 two three four five