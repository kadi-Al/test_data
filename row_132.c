#include <stdio.h>

int main() {
    unsigned int a, b;
    
    // Prompt user for input
    printf("Enter the first unsigned integer (a): ");
    scanf("%u", &a);
    printf("Enter the second unsigned integer (b): ");
    scanf("%u", &b);
    
    // Calculate and display the result of a - b
    if (a >= b) {
        printf("The result of %u - %u is: %u\n", a, b, a - b);
    } else {
        printf("Error: The first number must be greater than or equal to the second number.\n");
    }
    
    return 0;
}