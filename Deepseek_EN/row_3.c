#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>
#include <unistd.h>

#define MAX_INPUT_LEN 63  // Conservative DNS hostname limit
#define MAX_CMD_LEN 128

int isValidInput(const char *input) {
    while (*input) {
        if (!isalnum(*input) && *input != '-' && *input != '.') {
            return 0;
        }
        input++;
    }
    return 1;
}

int main() {
    char userInput[MAX_INPUT_LEN + 1];
    printf("Enter a hostname to ping: ");
    if (fgets(userInput, sizeof(userInput), stdin) != NULL) {
        // Remove newline character at the end of the input
        size_t len = strlen(userInput);
        if (len > 0 && userInput[len - 1] == '\n') {
            userInput[len - 1] = '\0';
        }

        if (!isValidInput(userInput)) {
            fprintf(stderr, "Invalid input: Input contains invalid characters.\n");
            return EXIT_FAILURE;
        }

        // Build the ping command with the user-provided hostname
        char cmd[MAX_CMD_LEN];
        snprintf(cmd, sizeof(cmd), "ping -c 4 %s", userInput);

        // Execute the ping command securely using popen()
        FILE *fp = popen(cmd, "w");
        if (fp == NULL) {
            perror("popen");
            return EXIT_FAILURE;
        }

        // Wait for the command to complete and read the output
        int status;
        if ((status = pclose(fp)) != 0) {
            fprintf(stderr, "Command execution failed with status %d.\n", status);
            return EXIT_FAILURE;
        }
    } else {
        perror("fgets");
        return EXIT_FAILURE;
    }

    return EXIT_SUCCESS;
}