class Person {
    int age;
    String name;
    int status; // Assuming status is an integer field for simplicity

    public Person(int age, String name) {
        this.age = age;
        this.name = name;
        this.status = 0; // Initialize status to 0
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Person{age=" + age + ", name='" + name + "', status=" + status + '}';
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new Person object and set the status to 0
        Person person = new Person(25, "John Doe");
        person.setStatus(0);

        // Print the details of the person
        System.out.println(person);
    }
}