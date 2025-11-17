public class RandomFloatToString {
    public static void main(String[] args) {
        // Generate three random floats
        float rand1 = (float) Math.random();
        float rand2 = (float) Math.random();
        float rand3 = (float) Math.random();

        // Convert them to strings using sprintf-like formatting
        String str1 = String.format("%.2f", rand1);
        String str2 = String.format("%.2f", rand2);
        String str3 = String.format("%.2f", rand3);

        // Print the results
        System.out.println("Random float 1: " + str1);
        System.out.println("Random float 2: " + str2);
        System.out.println("Random float 3: " + str3);
    }
}