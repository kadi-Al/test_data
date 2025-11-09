#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to escape HTML (simplified version)
char* escape_html(const char* input) {
    // Allocate memory for the escaped string
    char* output = malloc(strlen(input) * 6 + 1); // Each character can be replaced by up to 6 characters in HTML entity
    int k = 0;
    for (int i = 0; input[i] != '\0'; i++) {
        switch (input[i]) {
            case '<': sprintf(&output[k], "&lt;"); break;
            case '>': sprintf(&output[k], "&gt;"); break;
            case '&': sprintf(&output[k], "&amp;"); break;
            case '"': sprintf(&output[k], "&quot;"); break;
            default: output[k] = input[i]; break;
        }
        k++;
    }
    output[k] = '\0'; // Null-terminate the string
    return output;
}

// Function to handle HTTP request and generate a response
void http_hello_page(FILE* request, FILE* response) {
    char buffer[256];
    if (!fgets(buffer, sizeof(buffer), request)) {
        fprintf(response, "HTTP/1.1 400 Bad Request\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n");
        fprintf(response, "<html><body><h1>Bad Request</h1></body></html>");
        return;
    }

    // Find the username in the request (simplified approach)
    char* username = strstr(buffer, "username=");
    if (!username) {
        fprintf(response, "HTTP/1.1 400 Bad Request\r\nContent-Type: text/html; charset=UTF-8\r\n\r\n");
        fprintf(response, "<html><body><h1>Bad Request</h1></body></html>");
        return;
    }
    username += 8; // Move past "username="
    char* end = strchr(username, '&'); // Find the next ampersand to get the full username
    if (end) *end = '\0'; // Null-terminate the username string

    // Construct the response message
    char* escaped_username = escape_html(username);
    fprintf(response, "HTTP/1.1 200 OK\r\nContent-Type: text/plain; charset=UTF-8\r\n\r\n");
    fprintf(response, "Hello %s", escaped_username);

    // Free the allocated memory for the escaped username
    free(escaped_username);
}

int main() {
    FILE* request = fopen("request.txt", "r"); // Assuming a file named request.txt contains the HTTP request
    if (!request) {
        perror("Failed to open request file");
        return EXIT_FAILURE;
    }

    FILE* response = fopen("response.txt", "w"); // Open or create a file named response.txt for writing the response
    if (!response) {
        perror("Failed to open response file");
        fclose(request);
        return EXIT_FAILURE;
    }

    http_hello_page(request, response);

    // Close files
    fclose(request);
    fclose(response);

    return EXIT_SUCCESS;
}