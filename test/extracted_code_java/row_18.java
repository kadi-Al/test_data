public class Bond {
    private double couponRate; // Annual coupon rate (in percentage)
    private int maturityYear;  // Maturity year
    private double faceValue;   // Face value of the bond
    private double ytm;         // Yield to maturity (in percentage)

    public Bond(double couponRate, int maturityYear, double faceValue, double ytm) {
        this.couponRate = couponRate;
        this.maturityYear = maturityYear;
        this.faceValue = faceValue;
        this.ytm = ytm;
    }

    // Getters and setters for the bond properties
    public double getCouponRate() { return couponRate; }
    public void setCouponRate(double couponRate) { this.couponRate = couponRate; }
    public int getMaturityYear() { return maturityYear; }
    public void setMaturityYear(int maturityYear) { this.maturityYear = maturityYear; }
    public double getFaceValue() { return faceValue; }
    public void setFaceValue(double faceValue) { this.faceValue = faceValue; }
    public double getYtm() { return ytm; }
    public void setYtm(double ytm) { this.ytm = ytm; }
}
public class BondUtils {
    public static double calculateMacaulayDuration(Bond bond) {
        int yearsToMaturity = bond.getMaturityYear() - java.time.LocalDate.now().getYear();
        if (yearsToMaturity <= 0) throw new IllegalArgumentException("Bond has already matured.");
        
        double macaulayDuration = 0;
        double totalPV = 0;
        for (int t = 1; t <= yearsToMaturity; t++) {
            double cashFlow = bond.getFaceValue() * bond.getCouponRate();
            double discountFactor = Math.pow(1 + bond.getYtm() / 100, -t);
            double presentValue = cashFlow * discountFactor;
            macaulayDuration += (double) t * presentValue / bond.getFaceValue();
            totalPV += presentValue;
        }
        
        // Add the present value of the face value at maturity
        double finalCashFlow = bond.getFaceValue() + bond.getFaceValue() * bond.getCouponRate();
        double finalDiscountFactor = Math.pow(1 + bond.getYtm() / 100, yearsToMaturity);
        macaulayDuration += (double) yearsToMaturity * finalCashFlow * discountFactor / bond.getFaceValue() / totalPV;
        
        return macaulayDuration;
    }
}
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BondCalculator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Bond> bonds = new ArrayList<>();

        while (true) {
            System.out.println("Enter coupon rate (%), maturity year, face value, and yield to maturity (%):");
            double couponRate = scanner.nextDouble();
            int maturityYear = scanner.nextInt();
            double faceValue = scanner.nextDouble();
            double ytm = scanner.nextDouble();

            Bond bond = new Bond(couponRate, maturityYear, faceValue, ytm);
            bonds.add(bond);

            System.out.println("Do you want to add more bonds? (yes/no):");
            String answer = scanner.next().toLowerCase();
            if (!answer.equals("yes")) break;
        }

        for (Bond bond : bonds) {
            try {
                double duration = BondUtils.calculateMacaulayDuration(bond);
                System.out.println("The Macaulay Duration of the bond is: " + duration);
            } catch (Exception e) {
                System.err.println("Error calculating duration for this bond: " + e.getMessage());
            }
        }
    }
}