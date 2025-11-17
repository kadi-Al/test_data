import java.util.Scanner;

public class DynamicClassLoader {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the fully qualified class names (separated by commas):");
        String input = scanner.nextLine();
        
        // Split the input into an array of class names
        String[] classNames = input.split(",");
        
        try {
            for (String className : classNames) {
                // Trim any extra spaces and call Class.forName to load the class
                Class<?> clazz = Class.forName(className.trim());
                
                if (clazz != null && !clazz.isInterface() && !clazz.isEnum()) {
                    Object instance = clazz.getDeclaredConstructor().newInstance();
                    System.out.println("Loaded and instantiated class: " + className.trim());
                    
                    // You can now use the instance as needed
                    if (instance instanceof Runnable) {
                        ((Runnable) instance).run();
                    } else {
                        System.out.println(className.trim() + " is not runnable.");
                    }
                } else {
                    System.err.println("Class " + className.trim() + " is either an interface or enum, or could not be instantiated.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            scanner.close();
        }
    }
}