#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <ctype.h>

// Function to sanitize user input by escaping HTML characters
char* escapeHTML(const char* input) {
    if (input == NULL) return NULL;

    size_t len = strlen(input);
    char* output = (char*)malloc((len + 1) * sizeof(char)); // Allocate memory for the escaped string
    if (output == NULL) return NULL; // Check for allocation failure

    for (size_t i = 0; i < len; ++i) {
        switch (input[i]) {
            case '<': strcpy(&output[i], "&lt;"); break;
            case '>': strcpy(&output[i], "&gt;"); break;
            case '&': strcpy(&output[i], "&amp;"); break;
            case '"': strcpy(&output[i], "&quot;"); break;
            case '\'': strcpy(&output[i], "&#39;"); break;
            default: output[i] = input[i]; break;
        }
    }
    output[len] = '\0'; // Null-terminate the string
    return output;
}

int main() {
    char userInput[] = "<script>alert('XSS');</script>";
    char* escapedInput = escapeHTML(userInput);

    if (escapedInput != NULL) {
        printf("Displaying user input: %s\n", escapedInput);
        free(escapedInput); // Free the allocated memory
    } else {
        printf("Failed to process user input.\n");
    }

    return 0;
}