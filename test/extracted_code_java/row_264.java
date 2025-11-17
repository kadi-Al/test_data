import java.util.HashMap;
import java.util.Map;

public class HashMapRaceCondition {
    public static void main(String[] args) throws InterruptedException {
        Map<String, String> map = new HashMap<>();

        Thread writer1 = new Thread(() -> {
            for (int i = 0; i < 100; i++) {
                map.put(String.valueOf(i), "Value" + i);
            }
        });

        Thread writer2 = new Thread(() -> {
            for (int i = 100; i < 200; i++) {
                map.put(String.valueOf(i), "Value" + i);
            }
        });

        writer1.start();
        writer2.start();

        writer1.join();
        writer2.join();

        // Print the contents of the map after both threads have finished
        for (int i = 0; i < 200; i++) {
            System.out.println(map.get(String.valueOf(i)));
        }
    }
}
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ConcurrentHashMapExample {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        ExecutorService executor = Executors.newFixedThreadPool(2);

        Runnable writer1 = () -> {
            for (int i = 0; i < 100; i++) {
                map.put(String.valueOf(i), "Value" + i);
            }
        };

        Runnable writer2 = () -> {
            for (int i = 100; i < 200; i++) {
                map.put(String.valueOf(i), "Value" + i);
            }
        };

        executor.submit(writer1);
        executor.submit(writer2);

        executor.shutdown();
        executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);

        // Print the contents of the map after both threads have finished
        for (int i = 0; i < 200; i++) {
            System.out.println(map.get(String.valueOf(i)));
        }
    }
}