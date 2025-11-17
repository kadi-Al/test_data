class Person {
    int status; // Assuming status is an integer in this case

    public Person(int status) {
        this.status = status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return this.status;
    }
}

public class Main {
    public static void main(String[] args) {
        // Allocate memory for a new Person object and set its status to 0
        Person person = new Person(0);

        // Access the member of the struct through a reference (object in Java)
        System.out.println("Initial status: " + person.getStatus());

        // Change the status if needed
        person.setStatus(1); // For example, setting status to 1 instead of 0
        System.out.println("Updated status: " + person.getStatus());
    }
}