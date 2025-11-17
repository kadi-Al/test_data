public class IterationSequence {
    public static void main(String[] args) {
        // Define the array
        int[] numbers = {3, 4, 5};
        
        // Iterate through each number in the array
        for (int num : numbers) {
            // Calculate Fibonacci sequence for the current number
            long fibonacciResult = calculateFibonacci(num);
            
            // Calculate factorial of the Fibonacci result
            long factorialResult = calculateFactorial(fibonacciResult);
            
            // Print the results
            System.out.println("Number: " + num);
            System.out.println("Fibonacci Result: " + fibonacciResult);
            System.out.println("Factorial of Fibonacci Result: " + factorialResult);
            System.out.println();
        }
    }
    
    // Method to calculate Fibonacci sequence for a given number
    public static long calculateFibonacci(int n) {
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            long a = 0, b = 1;
            for (int i = 2; i <= n; i++) {
                long temp = a + b;
                a = b;
                b = temp;
            }
            return b;
        }
    }
    
    // Method to calculate factorial of a given number
    public static long calculateFactorial(long n) {
        if (n == 0 || n == 1) {
            return 1;
        } else {
            long result = 1;
            for (int i = 2; i <= n; i++) {
                result *= i;
            }
            return result;
        }
    }
}