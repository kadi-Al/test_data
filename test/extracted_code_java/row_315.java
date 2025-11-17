import java.util.Random;

public class RandomFloatToString {
    public static void main(String[] args) {
        // Create an instance of Random to generate random numbers
        Random rand = new Random();

        // Generate three random floats
        float float_a = rand.nextFloat();
        float float_b = rand.nextFloat();
        float float_c = rand.nextFloat();

        // Convert the floats to strings using a custom method or directly
        String str_a = floatToString(float_a);
        String str_b = floatToString(float_b);
        String str_c = floatToString(float_c);

        // Output the results (optional, you can also print them)
        System.out.println("str_a: " + str_a);
        System.out.println("str_b: " + str_b);
        System.out.println("str_c: " + str_c);
    }

    // Method to convert a float to a string with two decimal places
    public static String floatToString(float f) {
        return String.format("%.2f", f);
    }
}