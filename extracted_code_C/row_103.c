#include <stdio.h>
#include <string.h>

int main() {
    char dest[100]; // Make sure this is large enough to hold src + null terminator
    const char *src = "Hello, World!";

    if (strcpy(dest, src) != NULL) {
        printf("success\n");
    } else {
        printf("failure: destination buffer too small or source string longer than expected\n");
    }

    return 0;
}