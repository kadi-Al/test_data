import java.util.Random;

public class PasswordSalt {
    public static void main(String[] args) {
        // Specify a seed value to ensure deterministic randomness
        long seed = 12345L; // You can choose any long value as the seed

        // Create a new Random instance with the specified seed
        Random random = new Random(seed);

        // Generate some salt using nextInt()
        int saltLength = 16; // Define the length of the salt
        StringBuilder salt = new StringBuilder();
        for (int i = 0; i < saltLength; i++) {
            salt.append(Math.abs(random.nextInt()));
        }

        // Convert the StringBuilder to a String and print it
        String passwordSalt = salt.toString();
        System.out.println("Generated Password Salt: " + passwordSalt);
    }
}
Random random = new Random(seed);
int saltLength = 16; // Define the length of the salt
   StringBuilder salt = new StringBuilder();
   for (int i = 0; i < saltLength; i++) {
       salt.append(Math.abs(random.nextInt()));
   }
String passwordSalt = salt.toString();
   System.out.println("Generated Password Salt: " + passwordSalt);