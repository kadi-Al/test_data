#include <stdio.h>

void vulnerable_string_copy(char *src, char *dst) {
    while (*src != '\0') {
        *dst++ = *src++;
    }
}

int main() {
    char src[] = "Hello, World!";
    char dst[100]; // Ensure this is large enough to hold the source string or it will lead to buffer overflow.

    vulnerable_string_copy(src, dst);
    printf("Copied string: %s\n", dst);

    return 0;
}