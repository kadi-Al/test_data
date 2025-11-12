#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    // Define the data to be copied
    char data[] = "Hello, World!";
    
    // Allocate memory for 10 characters
    char *p = malloc(10);
    
    // Check if memory allocation was successful
    if (p == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    
    // Copy the data to the allocated memory
    memcpy(p, data, sizeof(data));
    
    // Print the copied data to verify
    printf("Copied data: %s\n", p);
    
    // Free the allocated memory
    free(p);
    
    return 0;
}