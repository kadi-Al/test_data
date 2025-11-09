import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class AESEncryption {
    public static void main(String[] args) throws Exception {
        String username = "exampleUser";
        int hash = username.hashCode();
        
        // Convert the hash to a byte array and use it as IV
        byte[] ivBytes = new byte[16];
        for (int i = 0; i < 16; i++) {
            ivBytes[i] = (byte) (hash >> (8 * (15 - i)));
        }
        
        // Initialize key and cipher
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        java.security.Key secretKey = keyGen.generateKey();
        
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivSpec);
        
        String plainText = "Hello, World!";
        byte[] encrypted = cipher.doFinal(plainText.getBytes());
        
        System.out.println("Encrypted: " + java.util.Base64.getEncoder().encodeToString(encrypted));
    }
}
SecureRandom secureRandom = new SecureRandom();
byte[] iv = new byte[16]; // AES-128 requires 16-bytes IV
secureRandom.nextBytes(iv);
IvParameterSpec ivSpec = new IvParameterSpec(iv);