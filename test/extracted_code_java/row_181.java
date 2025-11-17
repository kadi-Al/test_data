import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        Map<String, String> map = Collections.synchronizedMap(new HashMap<>());
        
        // Thread-safe version of the map is created using internal synchronization
    }
}
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class ConcurrentHashMapExample {
    public static void main(String[] args) {
        Map<String, String> map = new ConcurrentHashMap<>();
        
        // ConcurrentHashMap is designed for concurrent access
    }
}
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.HashMap;

public class SafeHashMap<K, V> {
    private final Map<K, V> map = new HashMap<>();
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    public void put(K key, V value) {
        lock.writeLock().lock();
        try {
            map.put(key, value);
        } finally {
            lock.writeLock().unlock();
        }
    }

    public V get(K key) {
        lock.readLock().lock();
        try {
            return map.get(key);
        } finally {
            lock.readLock().unlock();
        }
    }
}
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;
import java.util.HashMap;

public class SafeHashMap<K, V> {
    private final AtomicReference<Map<K, V>> mapRef = new AtomicReference<>(new HashMap<>());

    public void put(K key, V value) {
        Map<K, V> currentMap;
        do {
            currentMap = mapRef.get();
        } while (!mapRef.compareAndSet(currentMap, updateMapWithPut(currentMap, key, value)));
    }

    private Map<K, V> updateMapWithPut(Map<K, V> currentMap, K key, V value) {
        Map<K, V> newMap = new HashMap<>(currentMap);
        newMap.put(key, value);
        return newMap;
    }

    public V get(K key) {
        return mapRef.get().get(key);
    }
}