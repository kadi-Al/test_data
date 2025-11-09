import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CustomClassLoader extends ClassLoader {
    public Class<?> defineClassFromBytes(String name, byte[] b) throws Exception {
        return defineClass(name, b, 0, b.length);
    }

    public static void main(String[] args) throws Exception {
        // Example: Load from a local file
        String filePath = "path/to/your/classfile";
        byte[] classData = Files.readAllBytes(Paths.get(filePath));
        
        CustomClassLoader customClassLoader = new CustomClassLoader();
        Class<?> clazz = customClassLoader.defineClassFromBytes("ExampleClass", classData);
        
        System.out.println("Loaded class: " + clazz.getName());
    }
}