import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class SecureRandomGenerator {
    private static final String RNG_ALGORITHM = "SHA1PRNG"; // Recommended by NIST for strong randomness

    public static void main(String[] args) {
        try {
            // Create a secure random number generator instance
            SecureRandom secureRandom = SecureRandom.getInstance(RNG_ALGORITHM);

            // Generate and print a secure random integer
            int secureInt = getSecureRandomInteger(secureRandom);
            System.out.println("Generated secure integer: " + secureInt);

            // Generate and print a secure random byte array
            byte[] secureBytes = getSecureRandomBytes(secureRandom, 16); // Generating 16 bytes of entropy
            System.out.println("Generated secure bytes: " + java.util.Arrays.toString(secureBytes));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error initializing SecureRandom: " + e.getMessage());
        }
    }

    /**
     * Generates a secure random integer.
     * @param secureRandom the SecureRandom instance to use for generating entropy.
     * @return an int generated from the random bits provided by the RNG.
     */
    public static int getSecureRandomInteger(SecureRandom secureRandom) {
        return secureRandom.nextInt(); // This method can be modified to generate different types of numbers (e.g., long, byte[]) based on requirements
    }

    /**
     * Generates a specified number of secure random bytes.
     * @param secureRandom the SecureRandom instance to use for generating entropy.
     * @param numBytes the number of bytes to generate.
     * @return a byte array containing the generated random values.
     */
    public static byte[] getSecureRandomBytes(SecureRandom secureRandom, int numBytes) {
        byte[] randomBytes = new byte[numBytes];
        secureRandom.nextBytes(randomBytes); // Generates random bytes and places them into a user-supplied array
        return randomBytes;
    }
}