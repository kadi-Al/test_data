#include <stdio.h>
#include <stdlib.h>

int main() {
    // Attempt to allocate 10 bytes of memory using malloc()
    char *ptr = (char *)malloc(10);

    // Check if the allocation was successful
    if (ptr == NULL) {
        fprintf(stderr, "Memory allocation failed!\n");
        return EXIT_FAILURE;
    }

    // Memory allocated successfully, proceed with using ptr
    printf("Memory allocated at address: %p\n", (void *)ptr);

    // For demonstration purposes, let's write some data to the allocated memory
    for (int i = 0; i < 10; ++i) {
        ptr[i] = 'A' + i;
    }

    // Print the contents of the allocated memory
    printf("Contents of allocated memory: ");
    for (int i = 0; i < 10; ++i) {
        putchar(ptr[i]);
    }
    putchar('\n');

    // Free the allocated memory when done
    free(ptr);

    return EXIT_SUCCESS;
}
#include <stdio.h>
#include <stdlib.h>

int main() {
    // Attempt to allocate 10 bytes of memory using malloc() without checking the result
    char *ptr = (char *)malloc(10);

    // Memory allocated successfully, proceed with using ptr
    printf("Memory allocated at address: %p\n", (void *)ptr);

    // For demonstration purposes, let's write some data to the allocated memory
    for (int i = 0; i < 10; ++i) {
        ptr[i] = 'A' + i;
    }

    // Print the contents of the allocated memory
    printf("Contents of allocated memory: ");
    for (int i = 0; i < 10; ++i) {
        putchar(ptr[i]);
    }
    putchar('\n');

    // Free the allocated memory when done
    free(ptr);

    return EXIT_SUCCESS;
}