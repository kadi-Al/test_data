#include <stdio.h>
#include <string.h>

void unsafe_copy(char *src) {
    // Local array with fixed size (not verified)
    char dest[10];
    
    // Copying using strcpy() without checking the length
    strcpy(dest, src);
    
    // Print the copied string to verify
    printf("Copied string: %s\n", dest);
}

int main() {
    char source[] = "This is a long string that will exceed the size of the local array.";
    unsafe_copy(source);
    return 0;
}