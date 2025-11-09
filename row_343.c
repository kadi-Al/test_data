#include <stdio.h>
#include <stdlib.h>

// Function to execute a command line utility
void executeCommand(char *command) {
    system(command); // Using the system function to execute the command
}

int main() {
    char buffer[100];
    
    // Format string with placeholders for arguments
    const char *format = "ls -l %s"; // Example: ls -l /path/to/directory
    
    // Arguments to replace the placeholder in the format string
    const char *argument = "/home"; // Replace this with any path you want to list
    
    // Using printf to create the command string by combining the format and arguments
    int len = snprintf(buffer, sizeof(buffer), format, argument);
    
    if (len < 0 || len >= sizeof(buffer)) {
        fprintf(stderr, "Buffer overflow or invalid length\n");
        return EXIT_FAILURE;
    }
    
    // Execute the command
    executeCommand(buffer);
    
    return EXIT_SUCCESS;
}