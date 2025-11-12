#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Maximum length for the command including null terminator
#define MAX_COMMAND_LENGTH 128

int safe_system(const char *command) {
    if (command == NULL || strlen(command) >= MAX_COMMAND_LENGTH) {
        return -1; // Command is too long or invalid
    }

    // Execute the command using system call
    int result = system(NULL); // This sets up the environment for executing a command
    if (result == 0) {
        char *safe_command[2];
        safe_command[0] = strdup(command);
        safe_command[1] = NULL;

        // Execute the command without invoking the shell
        result = execve(command, safe_command, NULL);
        if (result == -1) {
            perror("safe_system");
        }

        free(safe_command[0]);
    } else {
        fprintf(stderr, "Command execution not supported\n");
        result = -1;
    }

    return result;
}

int main() {
    const char *test_command = "echo Hello, World!";
    if (safe_system(test_command) == 0) {
        printf("Command executed successfully\n");
    } else {
        printf("Failed to execute command\n");
    }
    return 0;
}