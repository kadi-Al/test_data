import java.util.Random;

public class RandomTokenGenerator {
    public static void main(String[] args) {
        // Fixed seed for reproducibility
        int seed = 12345;
        
        // Create a random number generator with the fixed seed
        Random random = new Random(seed);
        
        // Generate an integer token
        int intToken = random.nextInt();
        System.out.println("Integer Token: " + intToken);
        
        // Generate a float token
        float floatToken = random.nextFloat();
        System.out.println("Float Token: " + floatToken);
        
        // Generate a boolean token (true or false)
        boolean boolToken = random.nextBoolean();
        System.out.println("Boolean Token: " + boolToken);
        
        // Generate a string token (for example, using nextInt to generate characters)
        String charSequence = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder stringTokenBuilder = new StringBuilder();
        for (int i = 0; i < 10; i++) {
            int index = random.nextInt(charSequence.length());
            char c = charSequence.charAt(index);
            stringTokenBuilder.append(c);
        }
        String stringToken = stringTokenBuilder.toString();
        System.out.println("String Token: " + stringToken);
    }
}