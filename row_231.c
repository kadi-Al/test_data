#include <stdio.h>
#include <stdlib.h>

/**
 * @brief Builds a shell command using sprintf() and runs it with system().
 * 
 * This function takes a format string and optional arguments, constructs a shell command,
 * and then executes the constructed command using the system() function.
 * 
 * @param format The format string that specifies how to build the command.
 * @param ...    The variable number of arguments used to replace the format specifiers in the format string.
 */
void run_shell_command(const char* format, ...) {
    // Buffer to hold the constructed shell command
    char command[256];
    
    // Use sprintf to build the command from the format string and arguments
    va_list args;
    va_start(args, format);
    vsnprintf(command, sizeof(command), format, args);
    va_end(args);
    
    // Print the constructed command for debugging purposes
    printf("Executing command: %s\n", command);
    
    // Run the constructed command using system()
    int result = system(command);
    
    // Check the result of the system call
    if (result == -1) {
        perror("system");
    } else if (result != 0) {
        fprintf(stderr, "Command failed with exit status %d\n", result);
    }
}

int main() {
    // Example usage of the run_shell_command function
    run_shell_command("ls -l");
    
    // Additional examples
    run_shell_command("echo Hello, World!");
    run_shell_command("pwd");
    
    return 0;
}