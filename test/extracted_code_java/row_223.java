import java.util.Random;

public class TokenGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TOKEN_LENGTH = 32;

    private Random random;

    public TokenGenerator() {
        this.random = new Random(); // Use Random instead of SecureRandom for non-secure PRNG
    }

    public String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            token.append(randomChar);
        }
        return token.toString();
    }

    public static void main(String[] args) {
        TokenGenerator tokenGenerator = new TokenGenerator();
        String token = tokenGenerator.generateToken();
        System.out.println("Generated Token: " + token);
    }
}
import java.security.SecureRandom;

public class TokenGenerator {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int TOKEN_LENGTH = 32;

    private SecureRandom secureRandom;

    public TokenGenerator() {
        this.secureRandom = new SecureRandom(); // Use SecureRandom for more robust entropy source
    }

    public String generateToken() {
        StringBuilder token = new StringBuilder(TOKEN_LENGTH);
        for (int i = 0; i < TOKEN_LENGTH; i++) {
            int randomIndex = secureRandom.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            token.append(randomChar);
        }
        return token.toString();
    }

    public static void main(String[] args) {
        TokenGenerator tokenGenerator = new TokenGenerator();
        String token = tokenGenerator.generateToken();
        System.out.println("Generated Token: " + token);
    }
}