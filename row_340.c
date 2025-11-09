#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to execute the command specified in its argument
void executeCommand(const char *command) {
    // Execute the system call and capture the output
    FILE *fp;
    char path[1035];

    fp = popen(command, "r");
    if (fp == NULL) {
        printf("Failed to run command\n");
        return;
    }

    // Print the output a line at a time
    while (fgets(path, sizeof(path), fp) != NULL) {
        printf("%s", path);
    }

    if (pclose(fp) == -1) {
        printf("Command execution failed.\n");
    }
}

int main(int argc, char *argv[]) {
    // Check if the username is provided as a command line argument
    if (argc != 2) {
        printf("Usage: %s <username>\n", argv[0]);
        return EXIT_FAILURE;
    }

    // Construct the command with the given username
    char command[150]; // Buffer to hold the constructed command string
    snprintf(command, sizeof(command), "cat /etc/passwd | grep %s", argv[1]);

    // Execute the command
    executeCommand(command);

    return EXIT_SUCCESS;
}
bash
gcc -o userinfo userinfo.c
bash
./userinfo your_username