#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 1024

int main() {
    char *buf = (char *)malloc(BUFFER_SIZE);
    if (buf == NULL) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }

    char *buf1 = (char *)malloc(BUFFER_SIZE);
    if (buf1 == NULL) {
        perror("Failed to allocate memory");
        free(buf); // Free the previously allocated memory before exiting
        return EXIT_FAILURE;
    }

    printf("Enter up to 100 characters: ");
    if (fgets(buf, BUFFER_SIZE, stdin) != NULL) {
        // Ensure that only the first 100 characters are considered
        buf[strcspn(buf, "\n")] = '\0'; // Remove newline character if present
    } else {
        perror("Failed to read input");
        free(buf);
        free(buf1);
        return EXIT_FAILURE;
    }

    printf("Enter up to 100 characters for the second buffer: ");
    if (fgets(buf1, BUFFER_SIZE, stdin) != NULL) {
        // Ensure that only the first 100 characters are considered
        buf1[strcspn(buf1, "\n")] = '\0'; // Remove newline character if present
    } else {
        perror("Failed to read input");
        free(buf);
        free(buf1);
        return EXIT_FAILURE;
    }

    printf("Buffer 1: %s\n", buf);
    printf("Buffer 2: %s\n", buf1);

    // Free allocated memory
    free(buf);
    free(buf1);

    return EXIT_SUCCESS;
}