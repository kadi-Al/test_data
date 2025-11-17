import java.util.Scanner;

public class VehicleIndex {
    public static void main(String[] args) {
        // Sample array of vehicles
        String[] vehicles = {"Car", "Motorcycle", "Bicycle", "Truck", "Bus"};
        
        // Create a Scanner object to read input from the user
        Scanner scanner = new Scanner(System.in);
        
        // Prompt the user to enter an index
        System.out.print("Enter the index of the vehicle: ");
        int index = scanner.nextInt(); // Read the integer input from the user
        
        // Check if the entered index is within the bounds of the array
        if (index >= 0 && index < vehicles.length) {
            String selectedVehicle = vehicles[index]; // Access the vehicle at the given index
            System.out.println("The vehicle at index " + index + " is: " + selectedVehicle);
        } else {
            // If the index is out of bounds, inform the user
            System.out.println("Invalid index. Please enter a valid index between 0 and " + (vehicles.length - 1));
        }
        
        // Close the scanner to free up resources
        scanner.close();
    }
}