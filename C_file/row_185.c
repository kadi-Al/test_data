#include <stdio.h>
#include <string.h>

void unsafe_copy(char *dest, const char *src) {
    strcpy(dest, src);
}

void unsafe_format(char *buffer, const char *format, ...) {
    va_list args;
    va_start(args, format);
    vsprintf(buffer, format, args);
    va_end(args);
}

int main() {
    // Allocate a buffer that is too small to hold the source string
    char dest[5];
    const char *src = "Hello, World!";

    printf("Before copying: %s\n", dest);
    unsafe_copy(dest, src);  // This will cause a buffer overflow
    printf("After copying: %s\n", dest);

    // Allocate a small buffer that is too small to hold the formatted string
    char buffer[10];
    const char *fmt = "%s";
    const char *arg = "Hello, World!";

    printf("Before formatting: %s\n", buffer);
    unsafe_format(buffer, fmt, arg);  // This will cause a buffer overflow
    printf("After formatting: %s\n", buffer);

    return 0;
}