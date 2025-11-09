#include <stdio.h>
#include <stdlib.h>

int main() {
    int buffer_size = 10; // Define the size of the buffer
    char *buffer = (char *)malloc(buffer_size); // Allocate memory for the buffer

    if (buffer == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // Use the buffer... (for example, fill it with some data)
    for (int i = 0; i < buffer_size; i++) {
        buffer[i] = 'A' + i % 26; // Fill buffer with letters A-Z in a cyclic manner
    }

    // Free the buffer in a loop
    for (int i = 0; i <= buffer_size; i++) { // Note: Loop runs one extra time past the allocated size
        if (i < buffer_size) {
            printf("Freeing byte %d of the buffer\n", i);
        } else {
            printf("Buffer freed completely\n");
        }
        free(buffer + i); // Free each byte in the buffer, though this is incorrect usage as it frees single bytes incorrectly
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int main() {
    int buffer_size = 10; // Define the size of the buffer
    char *buffer = (char *)malloc(buffer_size); // Allocate memory for the buffer

    if (buffer == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // Use the buffer... (for example, fill it with some data)
    for (int i = 0; i < buffer_size; i++) {
        buffer[i] = 'A' + i % 26; // Fill buffer with letters A-Z in a cyclic manner
    }

    // Free the entire buffer at once
    free(buffer);

    return 0;
}