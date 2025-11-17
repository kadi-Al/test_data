#include <stdio.h>
#include <string.h>

void copy_input(const char *input) {
    // Define a local array with fixed size 64
    char local[64];
    
    // Attempt to copy the input into the local array
    strcpy(local, input);
    
    // Print the copied content for verification
    printf("Local Array Content: %s\n", local);
}

int main() {
    // Example input that is longer than 64 characters
    char long_input[] = "This is a very long string that exceeds the size of the local array.";
    
    // Call the function with the long input
    copy_input(long_input);
    
    return 0;
}
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void copy_input(const char *input) {
    // Determine the length of the input string
    size_t len = strlen(input);
    
    // Allocate memory for the local array, adding 1 to include space for the null terminator
    char *local = (char *)malloc((len + 1) * sizeof(char));
    
    if (local == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return;
    }
    
    // Copy the input into the allocated memory
    strcpy(local, input);
    
    // Print the copied content for verification
    printf("Local Array Content: %s\n", local);
    
    // Free the allocated memory
    free(local);
}

int main() {
    // Example input that is longer than 64 characters
    char long_input[] = "This is a very long string that exceeds the size of the local array.";
    
    // Call the function with the long input
    copy_input(long_input);
    
    return 0;
}