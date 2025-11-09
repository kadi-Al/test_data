#include <stdio.h>
#include <stdlib.h>

int main() {
    // Define the buffer size
    int buffer_size = 1024; // Example buffer size

    // Allocate memory for the buffer
    char *buffer = (char *)malloc(buffer_size * sizeof(char));
    if (buffer == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Free the buffer in a loop until it is freed successfully
    while (buffer != NULL) {
        free(buffer);
        buffer = NULL; // Ensure we don't try to free again if already freed
        printf("Buffer freed\n");
    }

    return 0;
}