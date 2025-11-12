#include <stdio.h>
#include <string.h>

int main() {
    char long_string[] = "This is a very long string that exceeds the allocated size of the destination buffer.";
    char dest[20]; // Destination buffer with insufficient space

    strcpy(dest, long_string); // Copying a longer string than the destination buffer

    printf("Destination: %s\n", dest);

    return 0;
}
#include <stdio.h>
#include <string.h>

int main() {
    char long_string[] = "This is a very long string that exceeds the allocated size of the destination buffer.";
    char dest[20]; // Destination buffer with insufficient space

    strncpy(dest, long_string, sizeof(dest) - 1); // Copying up to the size of the destination buffer minus one byte
    dest[sizeof(dest) - 1] = '\0'; // Ensure null-termination

    printf("Destination: %s\n", dest);

    return 0;
}