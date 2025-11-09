import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

public class PasswordHasher {

    public static void main(String[] args) {
        List<String> passwords = new ArrayList<>();
        passwords.add("password1");
        passwords.add("password2");
        passwords.add("password3");

        List<String> hashedPasswords = hashPasswords(passwords);

        for (int i = 0; i < passwords.size(); i++) {
            System.out.println("Password: " + passwords.get(i));
            System.out.println("Hashed Password: " + hashedPasswords.get(i));
        }
    }

    public static List<String> hashPasswords(List<String> passwords) {
        List<String> hashedPasswords = new ArrayList<>();
        for (String password : passwords) {
            String hashedPassword = getMD5Hash(password);
            hashedPasswords.add(hashedPassword);
        }
        return hashedPasswords;
    }

    public static String getMD5Hash(String input) {
        try {
            // Create MD5 Hash
            MessageDigest digest = MessageDigest.getInstance("MD5");
            digest.update(input.getBytes());
            byte[] messageDigest = digest.digest();

            // Convert the byte array into hexadecimal string
            StringBuilder hexString = new StringBuilder();
            for (byte b : messageDigest) {
                String hex = Integer.toHexString(0xFF & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }
}