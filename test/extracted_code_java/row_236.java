import java.util.HashMap;
import java.util.Map;

public class UserClassLoader {
    private Map<String, Class<?>> loadedClasses = new HashMap<>();

    public void addUserClass(String className) throws Exception {
        // Dynamically load the class from its name
        Class<?> clazz = Class.forName(className);
        loadedClasses.put(className, clazz);
    }

    public Object getInstance(String className) throws Exception {
        Class<?> clazz = loadedClasses.get(className);
        if (clazz == null) {
            throw new IllegalArgumentException("Class " + className + " not found.");
        }
        return clazz.getDeclaredConstructor().newInstance();
    }

    public static void main(String[] args) {
        UserClassLoader loader = new UserClassLoader();
        try {
            // Simulate user input
            String class1 = "java.util.HashMap";
            String class2 = "java.lang.String";

            loader.addUserClass(class1);
            loader.addUserClass(class2);

            Object mapInstance = loader.getInstance(class1);
            Object stringInstance = loader.getInstance(class2);

            // Treat instances as privileged objects
            System.out.println("Loaded and instantiated classes:");
            System.out.println("mapInstance: " + mapInstance.getClass().getName());
            System.out.println("stringInstance: " + stringInstance.getClass().getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public void addUserClass(String className) throws Exception {
    if (!isValidClassName(className)) {
        throw new IllegalArgumentException("Invalid class name.");
    }
    Class<?> clazz = Class.forName(className);
    loadedClasses.put(className, clazz);
}

private boolean isValidClassName(String className) {
    // Implement validation logic here
    return true; // Placeholder for actual implementation
}