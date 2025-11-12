FILE *popen(const char *command, const char *mode);
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int main() {
    char user_input[256];
    printf("Enter a command: ");
    fgets(user_input, sizeof(user_input), stdin);

    // Remove the newline character if present
    size_t len = strlen(user_input);
    if (len > 0 && user_input[len - 1] == '\n') {
        user_input[len - 1] = '\0';
    }

    // Execute the command using popen
    FILE *stream = popen(user_input, "r");
    if (stream == NULL) {
        perror("popen");
        return EXIT_FAILURE;
    }

    // Read and print the output of the command
    char buffer[256];
    while (fgets(buffer, sizeof(buffer), stream) != NULL) {
        printf("%s", buffer);
    }

    // Close the pipe
    pclose(stream);

    return EXIT_SUCCESS;
}