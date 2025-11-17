import java.util.Date;

public class Person {
    // Private fields to store birthdate, height, and weight
    private Date birthdate;
    private double height;
    private double weight;

    // Getter method for birthdate
    public Date getBirthdate() {
        return birthdate;
    }

    // Setter method for birthdate
    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    // Getter method for height
    public double getHeight() {
        return height;
    }

    // Setter method for height
    public void setHeight(double height) {
        this.height = height;
    }

    // Getter method for weight
    public double getWeight() {
        return weight;
    }

    // Setter method for weight
    public void setWeight(double weight) {
        this.weight = weight;
    }

    // Main method to test the Person class
    public static void main(String[] args) {
        // Create a new Person object
        Person person = new Person();

        // Set properties of the person
        person.setBirthdate(new Date()); // Setting birthdate to current date
        person.setHeight(5.8);           // Setting height in feet (e.g., 5.8 feet)
        person.setWeight(70.5);          // Setting weight in kilograms

        // Print the properties of the person
        System.out.println("Birthdate: " + person.getBirthdate());
        System.out.println("Height: " + person.getHeight() + " feet");
        System.out.println("Weight: " + person.getWeight() + " kg");
    }
}