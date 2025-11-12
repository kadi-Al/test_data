#include <stdio.h>
#include <stdlib.h>

int main() {
    int *ptr = NULL;
    int num = 5; // Number of integers to allocate memory for

    // Dynamically allocate memory for 'num' integers
    ptr = (int*)malloc(num * sizeof(int));
    
    // Check if the memory allocation was successful
    if (ptr == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // Assign values to the allocated memory
    for (int i = 0; i < num; ++i) {
        ptr[i] = i + 1; // Assigning values from 1 to 'num'
    }

    // Print the contents of the allocated memory before freeing
    printf("Contents of the allocated memory before free:\n");
    for (int i = 0; i < num; ++i) {
        printf("%d ", ptr[i]);
    }
    printf("\n");

    // Free the allocated memory
    free(ptr);

    // Attempt to print contents after freeing (undefined behavior)
    printf("Contents of the freed memory:\n");
    for (int i = 0; i < num; ++i) {
        printf("%d ", ptr[i]); // This is undefined behavior!
    }
    printf("\n");

    return 0;
}