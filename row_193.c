#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void forget_null_terminator(const char *str) {
    // Allocate strlen(str) bytes, but not enough for the null terminator
    char *allocated = (char *)malloc(strlen(str));
    
    if (allocated == NULL) {
        perror("Failed to allocate memory");
        return;
    }

    // Copy the string into the allocated space
    strcpy(allocated, str);

    // Print the content and size of the allocated space
    printf("Allocated string: %s\n", allocated);
    printf("Size of allocated space: %zu bytes\n", strlen(allocated));

    // Free the allocated memory
    free(allocated);
}

int main() {
    const char *test_str = "Hello, World!";
    forget_null_terminator(test_str);
    return 0;
}
Allocated string: Hello, World!
Size of allocated space: 13 bytes