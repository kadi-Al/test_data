class Person {
    int id;
    String name;
    int status; // Assuming status can be an integer or any other type you need.

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
        this.status = 0; // Initialize status to 0 when the person is created.
    }
}

public class Main {
    public static void main(String[] args) {
        // Allocate memory for a new Person object and set its status to 0.
        Person personPointer = new Person(1, "John Doe");

        // Access the members of the struct using the -> operator (in Java this is done via methods or direct access).
        System.out.println("Person ID: " + personPointer.id);
        System.out.println("Person Name: " + personPointer.name);
        System.out.println("Person Status: " + personPointer.status);
    }
}