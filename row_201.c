#include <stdio.h>
#include <string.h>

// Define the function to validate the password
int validate_password(const char *input) {
    // Define the expected password (first 4 characters must match this)
    const char *expected = "pass";
    
    // Check if the first 4 characters of the input match the expected string
    if (strncmp(input, expected, 4) == 0) {
        return 1; // Password is valid
    } else {
        return 0; // Password is invalid
    }
}

// Example usage
int main() {
    const char *password = "pass";
    
    if (validate_password(password)) {
        printf("Password is valid.\n");
    } else {
        printf("Password is invalid.\n");
    }
    
    return 0;
}