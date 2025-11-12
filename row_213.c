#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char command[256]; // Buffer to store user input

    printf("Enter a shell command: ");
    if (fgets(command, sizeof(command), stdin) != NULL) {
        // Remove newline character if present
        command[strcspn(command, "\n")] = 0;

        // Execute the command using system()
        int result = system(command);

        // Check if the command execution was successful
        if (result == -1) {
            perror("system");
        } else {
            printf("Command executed with return value: %d\n", result);
        }
    } else {
        fprintf(stderr, "Error reading input.\n");
    }

    return 0;
}
sh
  gcc -o run_command run_command.c
sh
  ./run_command