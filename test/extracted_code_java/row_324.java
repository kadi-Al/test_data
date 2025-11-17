public class SalesCalculator {
    public static void main(String[] args) {
        // Initialize sum to accumulate the monthly sales
        int sum = 0;
        
        // Call getMonthlySales for each iteration and add its return value to sum
        for (int i = 0; i < 3; i++) {
            sum += getMonthlySales(i);
        }
        
        // Print the total sum of monthly sales
        System.out.println("Total Sales: " + sum);
    }
    
    // Placeholder for a method that returns monthly sales data based on index
    public static int getMonthlySales(int monthIndex) {
        // Example values, replace with actual logic to fetch or calculate sales
        int[] salesData = {1000, 2000, 1500}; // Example sales for months 0, 1, and 2 respectively
        return salesData[monthIndex];
    }
}