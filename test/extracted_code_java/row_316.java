import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RandomFloatToString {
    public static void main(String[] args) {
        // Create an instance of the Random class
        Random random = new Random();

        // Generate three random floats
        float num1 = random.nextFloat();
        float num2 = random.nextFloat();
        float num3 = random.nextFloat();

        // Convert them to strings
        String str1 = Float.toString(num1);
        String str2 = Float.toString(num2);
        String str3 = Float.toString(num3);

        // Concatenate the strings into a single string
        String result = str1 + " " + str2 + " " + str3;

        // Write the string to a file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("random_floats.txt"))) {
            writer.write(result);
            System.out.println("Random floats have been written to random_floats.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}