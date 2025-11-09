class Person {
    int status;
}

public class Main {
    public static void main(String[] args) {
        // Allocate memory for a new Person object
        Person person = new Person();
        
        // Set the status of the person to 0
        person.status = 0;
        
        System.out.println("Status set to: " + person.status);
    }
}