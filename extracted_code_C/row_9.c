#include <stdio.h>
#include <stdlib.h>

int main() {
    int *buffer = (int *)malloc(sizeof(int) * 10);
    
    if (buffer == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Use the buffer...

    free(buffer); // Free the allocated memory once
    free(buffer); // Double free: undefined behavior

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int main() {
    int *buffer = (int *)malloc(sizeof(int) * 10);
    
    if (buffer == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Use the buffer...

    free(buffer); // Correct: free the allocated memory once

    return 0;
}