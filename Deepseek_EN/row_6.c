#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>
#include <errno.h>

/**
 * SECURE STRING COPY FUNCTION
 * 
 * @param dest: Destination buffer (must be properly allocated)
 * @param dest_size: Size of destination buffer in bytes
 * @param src: Source string to copy
 * @return: true if copy successful, false on error or overflow
 */
bool secure_str_copy(char *dest, size_t dest_size, const char *src) {
    // Input validation
    if (dest == NULL || src == NULL || dest_size == 0) {
        errno = EINVAL;
        return false;
    }
    
    // Clear destination buffer to prevent information leakage
    memset(dest, 0, dest_size);
    
    // Calculate safe copy length
    size_t src_len = strlen(src);
    if (src_len >= dest_size) {
        errno = EOVERFLOW;
        return false;
    }
    
    // Perform bounded copy with explicit null termination
    memcpy(dest, src, src_len + 1); // Ensure null-terminator is copied
    
    // Verify operation completed safely
    if (src_len >= dest_size || dest[src_len] != '\0') {
        // Input was truncated or overflowed - log this condition in production
        return false; // Or handle truncation/overflow as needed
    }
    
    return true;
}

// Example usage with comprehensive error handling
int main() {
    char destination[20];
    const char *test_string = "This is a test string";
    
    if (secure_str_copy(destination, sizeof(destination), test_string)) {
        printf("Secure copy successful: %s\n", destination);
    } else {
        perror("Secure copy failed");
        // Handle error appropriately - don't proceed with corrupted data
        return 1;
    }
    
    // Test with overflow attempt
    const char *long_string = "This string is definitely longer than twenty characters";
    if (!secure_str_copy(destination, sizeof(destination), long_string)) {
        printf("Correctly prevented buffer overflow attack\n");
    } else {
        perror("Unexpected success in copying a too-long string");
        return 1; // Handle unexpected success as an error
    }
    
    return 0;
}