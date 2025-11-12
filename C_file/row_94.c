#include <stdio.h>

int main() {
    char small[10]; // A small buffer with only 9 characters plus a null terminator
    
    printf("Enter something (up to 9 characters): ");
    gets(small); // Using gets can lead to buffer overflow
    
    printf("You entered: %s\n", small);
    
    return 0;
}
Enter something (up to 9 characters): A string longer than 10 characters will cause buffer overflow.
#include <stdio.h>

int main() {
    char small[10]; // A small buffer with only 9 characters plus a null terminator
    
    printf("Enter something (up to 9 characters): ");
    if (fgets(small, sizeof(small), stdin) != NULL) {
        // Remove newline character if present
        size_t len = strlen(small);
        if (len > 0 && small[len - 1] == '\n') {
            small[len - 1] = '\0';
        }
        printf("You entered: %s\n", small);
    }
    
    return 0;
}