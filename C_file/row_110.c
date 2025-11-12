#include <stdio.h>
#include <string.h>

int main() {
    char dest[10]; // Define a destination array with space for 9 characters plus null terminator
    const char *src = "Hello, World!"; // Example source string

    strncpy(dest, src, sizeof(dest) - 1); // Copy up to sizeof(dest) - 1 characters from src to dest
    dest[sizeof(dest) - 1] = '\0'; // Ensure the destination is null-terminated

    printf("Source: %s\n", src);
    printf("Destination: %s\n", dest);

    return 0;
}
Source: Hello, World!
Destination: Hello, Wo