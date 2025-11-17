import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Base64;

public class SecureIVDerivation {
    private static final int IV_LENGTH = 16; // AES block size
    private static final int SALT_LENGTH = 16;
    private static final int ITERATIONS = 10000;

    public static IvParameterSpec deriveIV(String username, byte[] salt) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("Username must be non-null and not empty");
        }
        if (salt == null || salt.length != SALT_LENGTH) {
            throw new IllegalArgumentException("Salt must be exactly " + SALT_LENGTH + " bytes long");
        }

        byte[] usernameBytes = username.getBytes();
        byte[] combinedData = Arrays.copyOf(usernameBytes, usernameBytes.length + salt.length);
        System.arraycopy(salt, 0, combinedData, usernameBytes.length, salt.length);

        try {
            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(new String(combinedData).toCharArray(), salt, ITERATIONS, IV_LENGTH * 8);
            byte[] key = factory.generateSecret(spec).getEncoded();

            // Use the first 16 bytes of the derived key as the IV
            return new IvParameterSpec(Arrays.copyOfRange(key, 0, IV_LENGTH));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Failed to derive IV", e);
        } finally {
            // Zeroize sensitive data from memory
            Arrays.fill(combinedData, (byte) 0);
            Arrays.fill(key, (byte) 0);
        }
    }

    private static byte[] generateRandomSalt() {
        byte[] salt = new byte[SALT_LENGTH];
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);
        return salt;
    }

    public static void main(String[] args) {
        String username = "exampleUser";
        byte[] salt = generateRandomSalt();
        IvParameterSpec iv = deriveIV(username, salt);
        System.out.println("Derived IV: " + Base64.getEncoder().encodeToString(iv.getIV()));
    }
}