#include <stdio.h>
#include <string.h> // for strlen()

void copy_with_off_by_one(char *buf, const char *str) {
    int len = strlen(str); // Get the length of the source string
    for (int i = 0; i <= len; i++) { // Include the null terminator in the loop
        buf[i] = str[i]; // Copy each character from str to buf
    }
}

int main() {
    const char *source_str = "Hello, World!"; // Source string
    char destination_buf[100]; // Destination buffer with sufficient size

    copy_with_off_by_one(destination_buf, source_str);

    printf("Source: %s\n", source_str);
    printf("Destination: %s\n", destination_buf);

    return 0;
}