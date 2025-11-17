#include <stdio.h>
#include <string.h>

int concatenate_strings(char *buffer, size_t buffer_size, const char *str1, const char *str2) {
    // Check if both strings and the null terminator fit into the buffer
    if (strlen(str1) + strlen(str2) >= buffer_size - 1) {
        return -1; // Return an error code if the buffer is too small
    }

    // Concatenate the two strings
    strcpy(buffer, str1);
    strcat(buffer, str2);

    return 0; // Success
}

int main() {
    char buffer[50];
    const char *str1 = "Hello, ";
    const char *str2 = "World!";

    if (concatenate_strings(buffer, sizeof(buffer), str1, str2) == 0) {
        printf("Concatenated string: %s\n", buffer);
    } else {
        printf("Error: Buffer too small to hold both strings.\n");
    }

    return 0;
}