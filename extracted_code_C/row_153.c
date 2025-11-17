#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Assuming libhttp provides a function to get query parameters
char* get_query_param(const char* url, const char* param) {
    // Simplified implementation for demonstration purposes
    // In a real-world scenario, you would use a proper URL parsing library
    char *start = strstr(url, "?");
    if (!start) return NULL;

    start += 1; // Move past the '?'
    while (*start && strncmp(start, param, strlen(param)) != 0) {
        while (*start && *start != '&' && *start != '=') {
            start++;
        }
        if (*start == '=') {
            start++; // Move past the '='
        }
    }
    if (!*start) return NULL;

    char *value_start = start;
    while (*start && *start != '&') {
        start++;
    }
    size_t length = start - value_start;
    char *value = (char*)malloc(length + 1);
    strncpy(value, value_start, length);
    value[length] = '\0';
    return value;
}

void handle_request(const char* url) {
    char* username = get_query_param(url, "username");
    if (username) {
        printf("Username: %s\n", username);
        free(username);
    } else {
        printf("No username provided.\n");
    }
}

int main() {
    const char* url = "http://example.com/path?username=john_doe";
    handle_request(url);
    return 0;
}