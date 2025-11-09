class Person {
    private String name;
    private int age;
    private double height;

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }
}

public class Main {
    public static void main(String[] args) {
        // Create a new Person object
        Person person = new Person("Alice", 30, 5.8);

        // Access members through references
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());
        System.out.println("Height: " + person.getHeight());

        // Modify the members using setter methods
        person.setName("Bob");
        person.setAge(25);
        person.setHeight(6.0);

        // Access modified members
        System.out.println("Updated Name: " + person.getName());
        System.out.println("Updated Age: " + person.getAge());
        System.out.println("Updated Height: " + person.getHeight());
    }
}