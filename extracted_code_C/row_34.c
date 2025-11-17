#include <stdio.h>

void my_unsafe_strcpy(char *dest, const char *src) {
    while (*src != '\0') {
        *dest = *src;
        dest++;
        src++;
    }
    *dest = '\0'; // Null-terminate the destination string
}

int main() {
    char src[] = "Hello, World!";
    char dest[20]; // Ensure this is large enough to hold the source string plus the null terminator

    my_unsafe_strcpy(dest, src);

    printf("Source: %s\n", src);
    printf("Destination: %s\n", dest);

    return 0;
}