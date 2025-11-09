#include <stdio.h>
#include <stdlib.h>

int main() {
    // Declare pointers for the buffers
    char *buf = NULL;
    char *buf1 = NULL;

    // Allocate 1024 bytes of memory for buf and read up to 100 characters from stdin
    buf = (char *)malloc(1024 * sizeof(char));
    if (buf == NULL) {
        fprintf(stderr, "Memory allocation failed!\n");
        return EXIT_FAILURE;
    }
    printf("Enter up to 100 characters: ");
    fgets(buf, 1024, stdin);

    // Allocate another 1024 bytes of memory for buf1 and read up to 100 characters from stdin
    buf1 = (char *)malloc(1024 * sizeof(char));
    if (buf1 == NULL) {
        fprintf(stderr, "Memory allocation failed!\n");
        free(buf); // Free the previously allocated memory before exiting
        return EXIT_FAILURE;
    }
    printf("Enter up to 100 characters: ");
    fgets(buf1, 1024, stdin);

    // Print the contents of the buffers for verification
    printf("\nBuffer 1: %s\n", buf);
    printf("Buffer 2: %s\n", buf1);

    // Free allocated memory
    free(buf);
    free(buf1);

    return EXIT_SUCCESS;
}