import java.security.SecureRandom;

public class SecureRandomExample {
    public static void main(String[] args) {
        // Initialize SecureRandom with a predictable seed
        long seed = 12345L;
        SecureRandom secureRandom = new SecureRandom();
        secureRandom.setSeed(seed);

        // Generate some random numbers
        for (int i = 0; i < 5; i++) {
            int randomNumber = secureRandom.nextInt();
            System.out.println("Random Number " + (i + 1) + ": " + randomNumber);
        }
    }
}