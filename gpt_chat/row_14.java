import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;

public class AesCtrIvGenerator {
    private static final int IV_SIZE = 16; // AES block size in bytes
    private static final String HKDF_ALGORITHM = "HKDF";
    private static final String HASH_ALGORITHM = "SHA-256"; // Use a secure hash algorithm for HMAC and HKDF

    public static void main(String[] args) {
        try {
            String username = "exampleUser"; // Example username
            IvParameterSpec ivSpec = generateIvFromUsername(username);
            System.out.println("Generated IV: " + Base64.getEncoder().encodeToString(ivSpec.getIV()));
        } catch (Exception e) {
            System.err.println("Error generating IV: " + e.getMessage());
        }
    }

    public static IvParameterSpec generateIvFromUsername(String username) throws NoSuchAlgorithmException {
        // Convert username to byte array using UTF-8 encoding
        byte[] usernameBytes = username.getBytes();

        // Generate a random salt (optional but recommended for HKDF)
        SecureRandom secureRandom = new SecureRandom();
        byte[] salt = new byte[16]; // Use a fixed salt size, e.g., 128 bits
        secureRandom.nextBytes(salt);

        // Use HKDF to derive the IV from the username and salt
        KeyGenerator keyGen = KeyGenerator.getInstance("HKDF");
        SecretKey secretKey = keyGen.generateKey(32, "HMAC" + HASH_ALGORITHM); // Generate a 256-bit key for HKDF

        javax.crypto.Mac mac = javax.crypto.Mac.getInstance(HASH_ALGORITHM);
        mac.init(secretKey);
        byte[] info = "aes-ctr-iv".getBytes(); // Info string to distinguish the purpose of the derived key
        mac.update(usernameBytes);
        mac.update(salt);
        byte[] prk = mac.doFinal();

        // Expand PRK into IV bytes
        byte[] iv = new byte[IV_SIZE];
        int infoLength = info.length;
        for (int i = 0; i < IV_SIZE; i++) {
            if (i < infoLength) {
                iv[i] = (byte) (prk[i] ^ info[i]);
            } else {
                mac.update(iv, 0, i); // Update MAC with current state of IV for full entropy
                iv[i] = (byte) (prk[i] ^ (mac.doFinal()[0])); // XOR with next byte of PRK
            }
        }

        return new IvParameterSpec(iv);
    }
}