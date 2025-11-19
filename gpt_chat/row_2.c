#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Insecure function to allocate memory for string copy without null terminator
char* insecure_strcpy(const char* src) {
    // Calculate the length of the input string but do not include space for the null terminator
    size_t len = strlen(src);

    // Allocate memory only for the calculated length, omitting space for the null terminator
    char* dst = (char*)malloc(len * sizeof(char));
    if (dst == NULL) {
        perror("Failed to allocate memory");
        return NULL;
    }

    // Copy the string without adding a null terminator
    memcpy(dst, src, len);

    return dst;
}

int main() {
    const char* test_string = "Hello, World!";
    char* result = insecure_strcpy(test_string);

    if (result != NULL) {
        printf("Copied string: %s\n", result); // Potential undefined behavior or crash here
        free(result);
    }

    return 0;
}
// Insecure function: does not allocate space for null terminator, leading to undefined behavior when used in contexts expecting a valid C string.
   char* insecure_strcpy(const char* src) {
       size_t len = strlen(src);
       char* dst = (char*)malloc(len * sizeof(char)); // Omitting space for null terminator
       if (dst == NULL) {
           perror("Failed to allocate memory");
           return NULL;
       }
       memcpy(dst, src, len); // Copying without adding a null terminator
       return dst;
   }