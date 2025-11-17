import java.util.*;

public class ByteArrayClassLoader extends ClassLoader {
    private Map<String, byte[]> classBytes;

    public ByteArrayClassLoader() {
        this.classBytes = new HashMap<>();
    }

    public void addClass(String name, byte[] bytes) {
        classBytes.put(name, bytes);
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        if (classBytes.containsKey(name)) {
            return defineClass(name, classBytes.get(name), 0, classBytes.get(name).length);
        }
        throw new ClassNotFoundException("Cannot find class: " + name);
    }
}
public class Main {
    public static void main(String[] args) {
        // Example of adding a class from a byte array
        ByteArrayClassLoader cl = new ByteArrayClassLoader();
        String className = "example.TestClass";
        byte[] bytes = compileClassFromSomewhere("package example; public class TestClass {}");
        cl.addClass(className, bytes);

        try {
            Class<?> loadedClass = cl.loadClass(className);
            System.out.println("Loaded class: " + loadedClass.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Dummy method to simulate getting byte code from somewhere
    private static byte[] compileClassFromSomewhere(String sourceCode) {
        // In a real application, you would use a compiler API or tool here
        return new byte[0]; // Placeholder for compiled class bytes
    }
}