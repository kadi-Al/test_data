#include <stdio.h>

void strncpy_non_null(char *dest, const char *src, size_t n) {
    for (size_t i = 0; i < n; ++i) {
        dest[i] = src[i];
    }
}

int main() {
    char dest[6]; // Ensure this is large enough to hold up to 5 characters plus a null terminator if needed.
    const char *src = "Hello, World!";
    
    strncpy_non_null(dest, src, 5);
    
    // Print the string manually since it's not null-terminated
    for (size_t i = 0; i < 5; ++i) {
        printf("%c", dest[i]);
    }
    printf("\n");

    return 0;
}