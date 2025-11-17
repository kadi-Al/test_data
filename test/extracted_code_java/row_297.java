// Define the Person class with fields name and status
class Person {
    String name;
    int status;

    // Constructor to initialize the person
    public Person(String name) {
        this.name = name;
        this.status = 0; // Initialize status to 0
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new Person object and set the status field to 0
        Person person = new Person("John Doe");

        // Output to verify that the allocation and initialization happened correctly
        System.out.println("Name: " + person.name);
        System.out.println("Status: " + person.status);
    }
}