#include <stdio.h> // Include standard input-output library for printf function

// Assuming getMonthlySales is defined somewhere in your code or provided by some external source
int getMonthlySales(int month) {
    // Example values, replace with actual logic if known
    int sales[] = {1000, 2000, 3000}; // Sales for January, February, March respectively
    return sales[month];
}

int main() {
    int sum = 0; // Initialize sum to store the total sales
    
    // For loop that iterates from 0 to 2 (inclusive)
    for(int i = 0; i <= 2; i++) {
        sum += getMonthlySales(i); // Add the return value of getMonthlySales(i) to sum
    }
    
    printf("Total Sales: %d\n", sum); // Print the total sales after the loop ends
    return 0;
}