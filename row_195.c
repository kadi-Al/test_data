#include <stdio.h>
#include <string.h>

void copy_string(const char *src, char *dest, size_t len) {
    if (src == NULL || dest == NULL || len <= 0) {
        return; // Return immediately if input parameters are invalid
    }
    memcpy(dest, src, len); // Copy the contents of src to dest without adding a null terminator
}

int main() {
    char source[] = "Hello, World!";
    char destination[13]; // Ensure this is large enough to hold the copied string plus a null terminator

    copy_string(source, destination, 13); // Copy the first 13 characters of source to destination

    printf("Source: %s\n", source);
    printf("Destination: %s\n", destination);

    return 0;
}