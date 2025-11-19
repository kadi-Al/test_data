#include <stdio.h>
#include <string.h>

// Function to securely copy a string with bounds checking
void secure_strncpy(char *dest, const char *src, size_t dest_size) {
    if (dest == NULL || src == NULL) {
        return; // Return immediately if inputs are invalid
    }

    strncpy(dest, src, dest_size - 1); // Copy the string up to the buffer size minus one to leave space for null terminator
    dest[dest_size - 1] = '\0'; // Ensure null-termination
}

int main() {
    const char *source = "Hello, World!";
    size_t buffer_size = sizeof(buffer); // Buffer size is determined by the allocated space for 'buffer'

    char buffer[12]; // Fixed-size buffer to hold the string

    secure_strncpy(buffer, source, buffer_size);

    printf("Copied string: %s\n", buffer);

    return 0;
}