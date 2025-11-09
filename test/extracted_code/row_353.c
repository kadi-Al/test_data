#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Function to get monthly sales data
double getMonthlySales(int month) {
    // Seed the random number generator with the current time
    srand(time(NULL));
    
    // Generate a random number between 0 and 100 for each month
    double sales = (double)(rand() % 101);
    
    return sales;
}

int main() {
    int months[12] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12};
    double sales[12];
    
    // Call getMonthlySales for each month and store the result in the array
    for (int i = 0; i < 12; i++) {
        sales[i] = getMonthlySales(months[i]);
        printf("Sales for Month %d: %.2f\n", months[i], sales[i]);
    }
    
    return 0;
}