#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void log_message(const char *format, ...) {
    FILE *log_file = fopen("log.txt", "a"); // Open the file in append mode
    if (log_file == NULL) {
        fprintf(stderr, "Error opening log file!\n");
        return;
    }

    va_list args;
    va_start(args, format);
    
    vfprintf(log_file, format, args); // Use vfprintf to handle variable arguments
    
    va_end(args);
    fclose(log_file);
}

int main() {
    char user_input[256];
    
    printf("Enter a message to log: ");
    if (fgets(user_input, sizeof(user_input), stdin) != NULL) {
        // Remove the newline character if present
        size_t len = strlen(user_input);
        if (len > 0 && user_input[len - 1] == '\n') {
            user_input[len - 1] = '\0';
        }
        
        log_message("%s\n", user_input); // Log the user input as a format string
    } else {
        fprintf(stderr, "Error reading input!\n");
    }
    
    return 0;
}