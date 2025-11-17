#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <stdbool.h>

#define MAX_INPUT_LENGTH 256
#define MAX_OUTPUT_LENGTH 512

// Function to check if the input contains any invalid characters or HTML tags
bool is_valid_input(const char *input) {
    // Check for length limit first, as it's a quick filter
    if (strlen(input) >= MAX_INPUT_LENGTH) {
        return false;
    }
    
    // Validate each character in the input string
    for (size_t i = 0; input[i] != '\0'; ++i) {
        // Check for printable characters only, reject others
        if (!isprint((unsigned char)input[i])) {
            return false;
        }
        
        // Basic check for HTML/JavaScript tags (simplified version)
        if (strchr("<>", input[i]) != NULL) {
            return false;
        }
    }
    
    return true;
}

// Function to encode user-controlled data for output in HTML
void html_encode(const char *input, char *output, size_t output_size) {
    // Use a safe bounded mechanism to avoid buffer overflows
    strncpy(output, input, output_size - 1);
    output[output_size - 1] = '\0';
    
    // Perform encoding for special HTML characters
    for (size_t i = 0; i < strlen(output); ++i) {
        switch (output[i]) {
            case '<': strcpy(&output[i], "&lt;"); break;
            case '>': strcpy(&output[i], "&gt;"); break;
            case '&': strcpy(&output[i], "&amp;"); break;
            case '"': strcpy(&output[i], "&quot;"); break;
            case '\'': strcpy(&output[i], "&#x27;"); break;
        }
    }
}

int main() {
    char user_input[MAX_INPUT_LENGTH];
    char encoded_output[MAX_OUTPUT_LENGTH];
    
    // Simulate getting user input (replace with actual input mechanism)
    printf("Enter search term: ");
    if (fgets(user_input, sizeof(user_input), stdin) == NULL) {
        fprintf(stderr, "Error reading input\n");
        return 1;
    }
    
    // Remove newline character from the input
    user_input[strcspn(user_input, "\n")] = '\0';
    
    // Validate the input before proceeding
    if (!is_valid_input(user_input)) {
        fprintf(stderr, "Invalid input detected\n");
        return 1;
    }
    
    // HTML encode the user input for safe output
    html_encode(user_input, encoded_output, sizeof(encoded_output));
    
    // Output the search term in a safe HTML format
    printf("<h1>Search: %s</h1>\n", encoded_output);
    
    return 0;
}