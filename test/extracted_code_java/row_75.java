xml
<dependencies>
    <!-- Other dependencies -->
    <dependency>
        <groupId>javax.crypto</groupId>
        <artifactId>javax.crypto-api</artifactId>
        <version>16.0.1</version>
    </dependency>
    <dependency>
        <groupId>org.bouncycastle</groupId>
        <artifactId>bcprov-jdk15on</artifactId>
        <version>1.69</version>
    </dependency>
</dependencies>
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.KeyGenerator;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.GCMParameterSpec;

public class SecretFile {
    public static void main(String[] args) throws IOException {
        // Generate a secret key for AES encryption
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128); // using 128-bit AES
        SecretKey secretKey = keyGen.generateKey();

        // Create the file to store encrypted data
        File secretFile = new File("secret.txt");

        // Encrypt some sample data and write it to the file
        byte[] plainText = "This is a secret message!".getBytes();
        Cipher cipher = Cipher.getInstance("AES/GCM/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedData = cipher.doFinal(plainText);

        // Write the encrypted data to the file
        Files.write(secretFile.toPath(), encryptedData);

        // Change the permissions of the file (this operation may vary by OS)
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            // Windows commands for changing file permissions are not straightforward and might require scripting
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c", "icacls secret.txt /setowner UserNameHere /inheritance:r");
            pb.start();
        } else {
            // Unix/Linux command to change the file permissions (requires superuser privileges depending on the system)
            ProcessBuilder pb = new ProcessBuilder("chmod", "600", "secret.txt");
            pb.start();
        }
    }
}