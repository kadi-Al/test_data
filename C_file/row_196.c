#include <stdio.h>  // For printf and scanf
#include <string.h> // For strcpy

// Function to copy user input into a fixed-size char array
void copyUserInput(char *buffer, size_t bufferSize) {
    if (buffer == NULL || bufferSize <= 0) {
        fprintf(stderr, "Invalid buffer or buffer size.\n");
        return;
    }

    // Clear the buffer to ensure it is empty before copying new data
    memset(buffer, 0, bufferSize);

    printf("Enter your input: ");
    fflush(stdout); // Ensure output is flushed immediately

    // Read user input from stdin
    if (fgets(buffer, bufferSize, stdin) != NULL) {
        // Remove the newline character if it was added by fgets
        size_t len = strlen(buffer);
        if (len > 0 && buffer[len - 1] == '\n') {
            buffer[len - 1] = '\0';
        }
    } else {
        fprintf(stderr, "Error reading input.\n");
    }
}

int main() {
    const size_t bufferSize = 100; // Define the fixed size of the char array
    char userInput[bufferSize];     // Declare the char array

    copyUserInput(userInput, bufferSize);

    printf("You entered: %s\n", userInput);

    return 0;
}