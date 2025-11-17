#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

// Function to check if the input string is valid
bool is_valid_input(const char* input) {
    return input != NULL && strlen(input) > 0;
}

// Secure function to perform a safe string operation
void safe_string_operation(const char* input) {
    // Input validation
    if (!is_valid_input(input)) {
        fprintf(stderr, "Error: Invalid or null input\n");
        return;
    }

    // Allocate memory for the string copy
    size_t len = strlen(input);
    char* ptr = malloc(len + 1);
    if (ptr == NULL) {
        fprintf(stderr, "Error: Memory allocation failed\n");
        return;
    }

    // Copy the input string to the allocated memory with bounds checking
    strncpy(ptr, input, len);
    ptr[len] = '\0';

    // Output the copied string for demonstration purposes
    printf("Copied String: %s\n", ptr);

    // Free the allocated memory and set the pointer to NULL (defensive programming)
    free(ptr);
    ptr = NULL;
}

// Secure alternative with automatic memory management
void secure_string_print(const char* input) {
    if (!is_valid_input(input)) {
        fprintf(stderr, "Error: Invalid or null input\n");
        return;
    }

    // Use stack allocation where possible to avoid heap allocation overhead and risks
    size_t len = strnlen(input, 1024);
    if (len == 0 || len >= 1024) {
        fprintf(stderr, "Error: Invalid string length\n");
        return;
    }

    char buffer[1024];
    strncpy(buffer, input, sizeof(buffer) - 1);
    buffer[sizeof(buffer) - 1] = '\0';

    printf("Printed String: %s\n", buffer);
}

int main() {
    const char* test_input = "Hello, World!";
    
    safe_string_operation(test_input);
    secure_string_print(test_input);

    return 0;
}