public class TokenChecker {
    private static final String configToken = "weakToken"; // This should be replaced with a secure and protected method to get the token

    public static void main(String[] args) {
        String userToken = "weakToken"; // User input or generated token, in real application this would come from an API or request

        if (checkToken(userToken)) {
            System.out.println("Tokens match!");
        } else {
            System.out.println("Tokens do not match.");
        }
    }

    public static boolean checkToken(String userToken) {
        return configToken.equals(userToken);
    }
}
public class TokenChecker {
    public static void main(String[] args) {
        String userToken = System.getenv("USER_TOKEN"); // Retrieve from environment variables

        if (checkToken(userToken)) {
            System.out.println("Tokens match!");
        } else {
            System.out.println("Tokens do not match.");
        }
    }

    public static boolean checkToken(String userToken) {
        String configToken = System.getenv("CONFIG_TOKEN"); // Retrieve from environment variables
        return configToken != null && configToken.equals(userToken);
    }
}