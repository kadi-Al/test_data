import java.security.NoSuchAlgorithmException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.concurrent.ThreadLocalRandom;

public class SecureRandomGenerator {
    private final SecureRandom secureRandom;

    public SecureRandomGenerator() {
        // Use the default system entropy source to seed SecureRandom securely
        try {
            this.secureRandom = SecureRandom.getInstanceStrong(); // Use strongest available algorithm
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Failed to initialize SecureRandom", e);
        }
    }

    public byte[] generateRandomBytes(int length) {
        byte[] randomBytes = new byte[length];
        secureRandom.nextBytes(randomBytes);
        return randomBytes;
    }

    // Example usage
    public static void main(String[] args) {
        SecureRandomGenerator generator = new SecureRandomGenerator();
        byte[] randomBytes = generator.generateRandomBytes(16);
        System.out.println("Generated random bytes: " + java.util.Arrays.toString(randomBytes));
    }
}