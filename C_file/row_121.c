#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void print_html(const char *user_input) {
    printf("Content-Type: text/html\n\n"); // Set the content type to HTML
    printf("<html>\n");
    printf("<head><title>User Input</title></head>\n");
    printf("<body>\n");
    printf("<h1>You entered:</h1>\n");
    printf("<p>%s</p>\n", user_input); // Print the user input
    printf("</body>\n");
    printf("</html>\n");
}

int main() {
    char *content_length = getenv("CONTENT_LENGTH");
    int length;
    if (content_length != NULL) {
        length = atoi(content_length);
    } else {
        length = 0;
    }

    // Allocate memory for the input buffer
    char *input_buffer = malloc(length + 1);
    if (input_buffer == NULL) {
        fprintf(stderr, "Unable to allocate memory\n");
        return 1;
    }

    // Read the input from stdin
    fread(input_buffer, 1, length, stdin);
    input_buffer[length] = '\0';

    // Print the user input on the HTML page
    print_html(input_buffer);

    // Free allocated memory
    free(input_buffer);

    return 0;
}