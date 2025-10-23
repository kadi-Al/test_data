public class PrimeNumbersPrinter {

    // Method to check if a number is prime
    public static boolean isPrime(int num) {
        // Handle the case for numbers less than 2 which are not prime
        if (num <= 1) {
            return false;
        }
        
        // Check divisibility from 2 up to the square root of num
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) {
                return false; // Not a prime number
            }
        }
        
        return true; // It's a prime number
    }

    public static void main(String[] args) {
        System.out.println("Prime numbers between 1 and 100:");
        for (int i = 1; i <= 100; i++) {
            if (isPrime(i)) {
                System.out.print(i + " ");
            }
        }
    }
}