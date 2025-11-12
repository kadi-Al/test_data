#include <stdio.h>
#include <stdlib.h>

int main() {
    int count = 1; // Example value that should be small enough to not cause overflow initially
    int total;
    char *buf;

    // Overflow the integer calculation intentionally
    while ((count * sizeof(int)) > INT_MAX) {
        count /= 2; // Reduce count until it causes an overflow
    }

    // Calculate the correct size after ensuring there's no overflow
    total = count * sizeof(int);

    // Allocate memory
    buf = (char *)malloc(total);
    if (buf == NULL) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }

    printf("Allocated %zu bytes\n", total);

    // Free the allocated memory
    free(buf);

    return 0;
}