#include <stdio.h>
#include <stdlib.h>

int main() {
    int count;

    // Prompt user for the number of elements
    printf("Enter the number of elements: ");
    scanf("%d", &count);

    // Allocate memory for the array
    int *arr = malloc(count * sizeof(int));
    if (arr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Optionally, initialize the array elements to zero or any other value
    for (int i = 0; i < count; i++) {
        arr[i] = 0; // Initialize each element to 0
    }

    // Display the allocated memory address and initial values
    printf("Array allocated at address: %p\n", (void*)arr);
    printf("Initial array elements:\n");
    for (int i = 0; i < count; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    // Optionally, free the allocated memory when it's no longer needed
    free(arr);

    return 0;
}