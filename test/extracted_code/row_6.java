import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class PrimeMultiples {
    private static final Object lock = new Object(); // Lock object for synchronization

    public static void main(String[] args) {
        int n = 7; // The number whose multiples we are looking for
        int a = 20, b = 50; // Range [a, b]

        ExecutorService executor = Executors.newFixedThreadPool(4);
        List<Future<?>> futures = new ArrayList<>();

        for (int i = n; i <= b; i += n) {
            if (i >= a && isPrime(i)) { // Check if the multiple is within range and prime
                Future<?> future = executor.submit(() -> {
                    synchronized (lock) {
                        System.out.println("Multiple of " + n + ": " + i);
                    }
                });
                futures.add(future);
            }
        }

        for (Future<?> future : futures) {
            try {
                future.get(); // Wait for all tasks to complete
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Thread execution failed: " + e.getMessage());
            }
        }

        executor.shutdown();
    }

    private static boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}