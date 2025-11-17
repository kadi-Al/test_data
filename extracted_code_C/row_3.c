#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void free_secret(char *key) {
    if (key != NULL) {
        // Allocate 100 bytes of memory for the string
        char *secret = malloc(100);
        
        // Copy the string "\key\" into the allocated memory
        strcpy(secret, key);
        
        // Free the allocated memory without clearing it
        free(secret);
    } else {
        printf("Invalid pointer passed to free_secret.\n");
    }
}

int main() {
    char *key = "\\key\\";  // Define the string to be copied
    free_secret(key);       // Call the function to free the memory
    
    return 0;
}