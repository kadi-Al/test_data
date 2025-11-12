#include <stdio.h>
#include <stdlib.h>

// Function to read user input into a buffer
char* read_input() {
    // Determine the size of the buffer dynamically
    int bufsize = 1024; // Initial buffer size, can be adjusted as needed
    char *buffer = malloc(bufsize);
    
    if (!buffer) {
        perror("Failed to allocate memory");
        exit(EXIT_FAILURE);
    }

    int position = 0;
    char c;

    // Read characters until newline or EOF is encountered
    while ((c = getchar()) != '\n' && c != EOF) {
        if (position >= bufsize - 1) {
            // Increase the buffer size if needed
            bufsize *= 2;
            char *new_buffer = realloc(buffer, bufsize);
            if (!new_buffer) {
                perror("Failed to allocate memory");
                free(buffer);
                exit(EXIT_FAILURE);
            }
            buffer = new_buffer;
        }
        buffer[position++] = c;
    }

    // Null-terminate the string and return the buffer
    if (c == EOF && position == 0) {
        free(buffer);
        return NULL;
    } else {
        buffer[position] = '\0';
        return buffer;
    }
}

// Example usage of the read_input function
int main() {
    char *user_input = read_input();
    if (user_input) {
        printf("You entered: %s\n", user_input);
        free(user_input); // Don't forget to free the allocated memory
    } else {
        printf("No input provided.\n");
    }
    return 0;
}