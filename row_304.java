// Define a simple Person class with name and status fields
class Person {
    String name;
    int status;

    // Constructor to initialize the person object
    public Person(String name, int status) {
        this.name = name;
        this.status = status;
    }

    // Method to display the details of the person
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Status: " + status);
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new Person object and set the values using references
        Person person = new Person("John", 0);

        // Display the details of the person
        person.displayDetails();
    }
}