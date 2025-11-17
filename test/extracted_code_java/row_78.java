import java.util.Random;
import java.text.DecimalFormat;

public class RandomFloatFormatting {
    public static void main(String[] args) {
        // Create a random number generator
        Random random = new Random();
        
        // Generate three random floats
        float float1 = random.nextFloat() * 100;  // between 0 and 100
        float float2 = random.nextFloat() * 100;  // between 0 and 100
        float float3 = random.nextFloat() * 100;  // between 0 and 100
        
        // Create a DecimalFormat object to format the floats into strings with two decimal places
        DecimalFormat df = new DecimalFormat("#.##");
        
        // Format the floats into strings using the DecimalFormat
        String formattedFloat1 = df.format(float1);
        String formattedFloat2 = df.format(float2);
        String formattedFloat3 = df.format(float3);
        
        // Store the formatted strings in variables
        String floatString1 = "Random Float 1: " + formattedFloat1;
        String floatString2 = "Random Float 2: " + formattedFloat2;
        String floatString3 = "Random Float 3: " + formattedFloat3;
        
        // Print the formatted strings to console
        System.out.println(floatString1);
        System.out.println(floatString2);
        System.out.println(floatString3);
    }
}