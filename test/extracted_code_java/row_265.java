public class Singleton {
    // The private static instance variable to hold the single instance of the class.
    private static volatile Singleton instance;

    // A private constructor to prevent instantiation from other classes.
    private Singleton() {
        // Initialization code, if needed.
    }

    // Public method to provide access to the singleton instance.
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}
public class Main {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        // Use the singleton instance as needed...
    }
}