#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char user_input[100]; // Buffer to store user input
    printf("Enter the size: ");
    fgets(user_input, sizeof(user_input), stdin);
    
    // Remove newline character if present
    user_input[strcspn(user_input, "\n")] = 0;
    
    char *endptr;
    long parsed_size = strtol(user_input, &endptr, 10);
    
    // Check for various possible errors
    if (endptr == user_input || *endptr != '\0') {
        fprintf(stderr, "Invalid input. Please enter a valid number.\n");
        return EXIT_FAILURE;
    }
    
    if (parsed_size <= 0) {
        fprintf(stderr, "Size must be positive.\n");
        return EXIT_FAILURE;
    }
    
    // Allocate memory
    int size = (int)parsed_size;
    void *ptr = malloc(size);
    
    if (!ptr) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }
    
    // Memory allocated successfully
    printf("Memory allocated at address: %p\n", ptr);
    
    // Free the allocated memory when no longer needed
    free(ptr);
    
    return 0;
}