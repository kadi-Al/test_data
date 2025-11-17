import java.io.*;

public class DeserializationExample {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        // Create an instance of ObjectOutputStream and write a string to it (the name of the class)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeUTF("com.example.MyClass"); // Write the class name here
        objectOutputStream.close();

        // Convert the stream to bytes and then create an ObjectInputStream from it
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArray);
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);

        // Read the class name from the stream (this step is simplified for demonstration)
        String className = objectInputStream.readUTF();

        // Now, we will try to instantiate the class by its name
        Class<?> clazz = Class.forName(className);
        Object instance = clazz.getDeclaredConstructor().newInstance();

        System.out.println("Instantiated class: " + instance.getClass().getName());
    }
}