#include <stdio.h>
#include <string.h>
#include <secure_lib.h> // Assuming a secure library header file exists

// Function to demonstrate buffer overflow risks using bounds-checked functions
void demonstrate_buffer_overflow_risks(void) {
    char source[12] = "123456789";
    char dest_secure[8];
    char dest_unsafe[8];
    
    // SECURE IMPLEMENTATION - Bounds-checked copy
    printf("=== SECURE IMPLEMENTATION ===\n");
    if(strncpy_s(dest_secure, sizeof(dest_secure), source, _TRUNCATE) != 0) {
        fprintf(stderr, "Security: Input truncation detected and handled\n");
    }
    printf("Secure result: %s\n", dest_secure);
    
    // VULNERABLE DEMONSTRATION (for educational purposes only)
    printf("\n=== VULNERABLE DEMONSTRATION ===\n");
    printf("WARNING: This demonstrates unsafe coding practices\n");
    
    // Compiler warnings expected here - intentional educational example
    #pragma GCC diagnostic push
    #pragma GCC diagnostic ignored "-Wstringop-overflow"
    strcpy(dest_unsafe, source); // UNSAFE - for demonstration only
    #pragma GCC diagnostic pop
    
    printf("Unsafe result: %s\n", dest_unsafe);
}

// Production-safe string copy function using secure library functions
errno_t secure_string_copy(char *dest, size_t dest_size, const char *src) {
    if (dest == NULL || src == NULL || dest_size == 0) {
        return EINVAL; // Invalid argument error code
    }
    
    // Input validation to prevent buffer overflow
    size_t src_len = strnlen(src, dest_size);
    if (src_len >= dest_size) {
        dest[0] = '\0'; // Ensure null termination if input is too long
        return ERANGE; // Range error code indicating the source string does not fit into the destination buffer
    }
    
    return strncpy_s(dest, dest_size, src, _TRUNCATE); // Copy with bounds checking and truncation
}

int main(void) {
    demonstrate_buffer_overflow_risks();
    
    // Production usage example
    char buffer[8];
    if (secure_string_copy(buffer, sizeof(buffer), "123456789") != 0) {
        fprintf(stderr, "Security: Input validation failed - potential overflow prevented\n");
        return 1; // Handle error condition appropriately
    }
    
    printf("Production usage: %s\n", buffer);
    return 0;
}
bash
gcc -D_FORTIFY_SOURCE=2 -fstack-protector-all -Werror -Wall -Wextra -Wformat-security secure_example.c -o secure_example