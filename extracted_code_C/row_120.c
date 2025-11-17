#include <stdio.h>
#include <string.h> // Include the string header for memcpy

int main() {
    char src[] = "Hello, World!";
    char dest[50]; // Ensure this is large enough to hold the data or dynamically allocate if unknown size

    // Correct usage of memcpy: specify the number of bytes to copy
    size_t size = sizeof(src) - 1; // Subtract 1 to exclude the null terminator in string literals
    memcpy(dest, src, size);

    printf("Source: %s\n", src);
    printf("Destination: %s\n", dest);

    return 0;
}