import java.util.ArrayList;
import java.util.List;

public class Accumulator {
    public static void main(String[] args) {
        // Example input: value1=10, value2=20, value3=30 (as query parameters in a URL or form submission)
        List<String> values = new ArrayList<>();
        values.add("10");
        values.add("20");
        values.add("30");

        int total = 0;
        for (String value : values) {
            try {
                int num = Integer.parseInt(value);
                total += num;
            } catch (NumberFormatException e) {
                // Handle the exception if needed, but since we're not guarding against overflow, this might be omitted
                System.out.println("Warning: Invalid number format encountered.");
            }
        }

        System.out.println("Total sum of valid integers: " + total);
    }
}
import java.util.ArrayList;
import java.util.List;

public class Accumulator {
    public static void main(String[] args) {
        List<String> values = new ArrayList<>();
        values.add("10");
        values.add("20");
        values.add("30");

        long total = 0; // Use long to avoid overflow for large numbers
        for (String value : values) {
            try {
                int num = Integer.parseInt(value);
                if ((total > 0 && num > Integer.MAX_VALUE - total) || (total < 0 && num < Integer.MIN_VALUE - total)) {
                    throw new ArithmeticException("Integer overflow");
                }
                total += num;
            } catch (NumberFormatException e) {
                System.out.println("Warning: Invalid number format encountered.");
            } catch (ArithmeticException e) {
                System.out.println(e.getMessage());
            }
        }

        System.out.println("Total sum of valid integers: " + total);
    }
}