#include <stdio.h>

int main() {
    // Declare the variable to hold total sales
    float totalSales = 0;

    // Add the sales for each month to totalSales
    totalSales += 12345.67;  // January sales
    totalSales += 23456.78;  // February sales
    totalSales += 34567.89;  // March sales

    // Print the total sales to the screen
    printf("Total Sales: $%.2f\n", totalSales);

    return 0;
}