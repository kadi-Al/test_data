// Define the Sales interface with getMonthlySales method
interface Sales {
    double[] getMonthlySales();
}

// Implement the Sales interface in a concrete class
class CompanySales implements Sales {
    @Override
    public double[] getMonthlySales() {
        // Replace this with actual logic to fetch monthly sales data
        return new double[]{1000.0, 1500.0, 1200.0, 800.0, 1300.0, 1600.0, 1400.0, 1700.0, 1900.0, 2000.0, 2100.0, 2200.0};
    }
}

// Main class to call getMonthlySales 12 times
public class SalesReport {
    public static void main(String[] args) {
        // Create an instance of the CompanySales class
        CompanySales companySales = new CompanySales();
        
        // Call getMonthlySales 12 times and print each month's sales
        double[] monthlySales = companySales.getMonthlySales();
        for (int i = 0; i < 12; i++) {
            System.out.println("Month " + (i + 1) + ": " + monthlySales[i]);
        }
    }
}