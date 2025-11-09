import java.util.Random;
import java.util.UUID;

public class SessionTokenGenerator {
    private static final int SESSION_TOKEN_LENGTH = 16; // Length of the session token (in characters)

    public static String generateSessionToken() {
        Random random = new Random();
        byte[] bytes = new byte[SESSION_TOKEN_LENGTH];
        random.nextBytes(bytes);
        
        // Convert byte array to a hexadecimal string
        StringBuilder tokenBuilder = new StringBuilder();
        for (byte b : bytes) {
            tokenBuilder.append(String.format("%02x", b));
        }
        
        return tokenBuilder.toString();
    }

    public static void main(String[] args) {
        String sessionToken = generateSessionToken();
        System.out.println("Generated Session Token: " + sessionToken);
    }
}
import java.util.UUID;

public class SessionTokenGenerator {
    public static String generateSessionToken() {
        return UUID.randomUUID().toString();
    }

    public static void main(String[] args) {
        String sessionToken = generateSessionToken();
        System.out.println("Generated Session Token: " + sessionToken);
    }
}