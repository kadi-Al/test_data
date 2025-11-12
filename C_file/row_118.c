#include <stdio.h>
#include <stdlib.h>

void unsafe_function(char *ptr) {
    free(ptr); // Freeing the pointer
    printf("%s", ptr); // Attempting to print the freed memory
}

int main() {
    char *memory = malloc(10 * sizeof(char)); // Allocating memory
    if (memory == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    snprintf(memory, 10, "Hello, World!"); // Writing to the allocated memory
    unsafe_function(memory); // Passing the pointer to the unsafe function

    free(memory); // Freeing the memory again (though it's already freed)
    return 0;
}