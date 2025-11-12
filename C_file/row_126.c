#include <stdio.h>

// Function to convert float to int (without range validation)
int floatToInt(float num) {
    // Cast the float to an int, which truncates the decimal part
    int result = (int)num;
    return result;
}

int main() {
    float floatNum = 3.14159f;
    int intNum = floatToInt(floatNum);
    
    printf("Original float: %.2f\n", floatNum);
    printf("Converted to int: %d\n", intNum);
    
    return 0;
}