#include <stdio.h>
#include <stdlib.h>

// Function to safely access an element in the array with index validation
int safe_array_access(int* array, int size, int index) {
    // Check if the index is within the bounds of the array
    if (index >= 0 && index < size) {
        return array[index]; // Return the value at the valid index
    } else {
        // Handle invalid index gracefully
        fprintf(stderr, "Error: Index out of bounds.\n");
        exit(EXIT_FAILURE); // Exit the program to avoid undefined behavior in accessing memory outside the array.
    }
}

int main() {
    int array[5] = {10, 20, 30, 40, 50};
    int size = sizeof(array) / sizeof(array[0]);

    // Example valid index
    int valid_index = 2;
    printf("Value at valid index: %d\n", safe_array_access(array, size, valid_index));

    // Example invalid index
    int invalid_index = 10;
    printf("Attempting to access value at invalid index: %d\n", safe_array_access(array, size, invalid_index));

    return 0;
}