int *ptr = (int*)0x1234; // Bad type conversion: assigning address to pointer
*ptr = 100;              // Assigning an integer value to the pointer
#include <stdio.h>
#include <stdlib.h>

int main() {
    int *ptr = (int*)malloc(sizeof(int)); // Allocate memory for one integer
    if (ptr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    *ptr = 100; // Assign the value 100 to the allocated memory
    printf("Value at ptr: %d\n", *ptr);
    free(ptr);   // Free the allocated memory
    return 0;
}