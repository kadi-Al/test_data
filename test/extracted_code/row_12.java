import java.util.ArrayList;
import java.util.Scanner;

public class FibonacciList {
    public static void main(String[] args) {
        ArrayList<Integer> fibonacciList = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the elements of the Fibonacci sequence, separated by spaces:");
        String input = scanner.nextLine();
        String[] elements = input.split(" ");

        try {
            for (String element : elements) {
                int num = Integer.parseInt(element);
                if (!isFibonacciSequence(fibonacciList, num)) {
                    throw new IllegalArgumentException("Input does not represent a Fibonacci sequence.");
                }
                fibonacciList.add(num);
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input format. Please enter integers separated by spaces.");
            scanner.close();
            return;
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            scanner.close();
            return;
        }

        System.out.println("The Fibonacci sequence you entered is: " + fibonacciList);
        scanner.close();
    }

    private static boolean isFibonacciSequence(ArrayList<Integer> list, int num) {
        if (list.isEmpty()) {
            return true; // The first element can be any non-negative integer.
        } else {
            int size = list.size();
            if (size == 1) {
                return true; // Second element can also be any non-negative integer.
            } else {
                int last = list.get(size - 1);
                int secondLast = list.get(size - 2);
                return num == last + secondLast;
            }
        }
    }
}