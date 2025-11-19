#include <stdio.h>
#include <stdlib.h>

// Function to safely free a pointer after using it
void safe_free(int **ptr) {
    if (*ptr != NULL) {
        int *temp = *ptr; // Temporarily store the value of ptr to avoid use-after-free
        *ptr = NULL;      // Set the pointer to NULL after using it
        free(temp);       // Safely free the allocated memory
    } else {
        printf("Pointer is already null, nothing to free.\n");
    }
}

int main() {
    int *p = (int *)malloc(sizeof(int)); // Allocate memory for an integer
    if (p == NULL) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }

    *p = 42; // Assign a value to the allocated memory
    printf("Value at p: %d\n", *p); // Use the pointer safely before freeing

    safe_free(&p); // Safely free the pointer

    if (p != NULL) {
        printf("Unexpectedly, p is not null after freeing!\n");
    } else {
        printf("p is now null as expected.\n");
    }

    return 0;
}