#include <stdio.h>
#include <string.h>
#include <stdlib.h>

// Function to securely copy strings, ensuring no buffer overflow
void secure_strcpy(char *dest, const char *src, size_t dest_size) {
    if (dest == NULL || src == NULL || dest_size <= 0) {
        return; // Exit function immediately in case of invalid input
    }

    size_t src_len = strlen(src);
    if (src_len >= dest_size) {
        // If the source string is too long to fit into the destination, truncate it
        src_len = dest_size - 1;
    }

    memcpy(dest, src, src_len + 1); // Copy including null terminator
    dest[src_len] = '\0'; // Ensure null termination after copy
}

int main() {
    char long_string[] = "This is a very long string that might exceed the buffer size if not handled properly.";
    const size_t buffer_size = sizeof(long_string);
    char buffer[buffer_size];

    // Attempt to copy the potentially malicious input into the buffer
    secure_strcpy(buffer, long_string, buffer_size);

    printf("Copied string: %s\n", buffer);

    return 0;
}