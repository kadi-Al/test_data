public class IdCommand {
    public static void main(String[] args) {
        // Check if a username was provided as a command line argument
        if (args.length == 0) {
            System.out.println("Usage: java IdCommand <username>");
            return;
        }

        String username = args[0];

        try {
            // Execute the id command with the provided username
            Process process = Runtime.getRuntime().exec("id " + username);

            // Wait for the process to complete and get the exit status
            int exitStatus = process.waitFor();

            // Print the result based on the exit status
            if (exitStatus == 0) {
                System.out.println("Command succeeded.");
            } else {
                System.out.println("Command failed.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
    }
}
sh
java IdCommand your_username