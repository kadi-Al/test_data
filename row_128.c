#include <stdlib.h>

void* reallocate_and_clear(void** ptr, size_t new_size) {
    if (*ptr != NULL) {
        free(*ptr);  // Free the existing memory
    }
    
    *ptr = (void*)malloc(new_size);  // Allocate new memory
    if (*ptr == NULL) {
        return NULL;  // Allocation failed, handle error appropriately
    }
    
    // Optionally clear the allocated memory to a specific value (e.g., zero)
    for (size_t i = 0; i < new_size; ++i) {
        ((char*)(*ptr))[i] = 0;
    }
    
    return *ptr;
}
#include <stdio.h>

int main() {
    int* arr = (int*)malloc(5 * sizeof(int));  // Initial allocation
    if (arr == NULL) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }
    
    // Initialize the array with some values for demonstration
    for (int i = 0; i < 5; ++i) {
        arr[i] = i + 1;
    }
    
    printf("Original array: ");
    for (int i = 0; i < 5; ++i) {
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    // Reallocate and clear the memory
    if (reallocate_and_clear((void**)&arr, 10 * sizeof(int)) == NULL) {
        perror("Failed to reallocate memory");
        free(arr);  // Free the original allocated memory before exiting
        return EXIT_FAILURE;
    }
    
    printf("Reallocated array: ");
    for (int i = 0; i < 10; ++i) {
        arr[i] = i * 2;
        printf("%d ", arr[i]);
    }
    printf("\n");
    
    free(arr);  // Free the memory when done using it
    return EXIT_SUCCESS;
}