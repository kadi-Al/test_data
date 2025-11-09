import java.util.Scanner;

public class VehicleIndexPrinter {
    public static void main(String[] args) {
        // Define a sample array of vehicles
        String[] vehicles = {"Car", "Bike", "Truck", "Motorcycle", "Airplane"};

        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);

        // Prompt the user to enter an index
        System.out.print("Enter the index of the vehicle: ");
        int index = scanner.nextInt(); // Read the integer entered by the user

        // Check if the entered index is within the bounds of the array
        if (index >= 0 && index < vehicles.length) {
            String selectedVehicle = vehicles[index]; // Access the vehicle at the given index
            System.out.println("The vehicle at index " + index + " is: " + selectedVehicle);
        } else {
            System.out.println("Invalid index entered.");
        }

        // Close the scanner to free up resources
        scanner.close();
    }
}