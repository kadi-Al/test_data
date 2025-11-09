#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <unistd.h> // for fork(), exec(), etc.
#include <sys/wait.h> // for wait()

// Function to run the command and get the exit status
int run_command(char *username) {
    int status;
    pid_t pid = fork();

    if (pid == 0) {
        // Child process
        char cmd[256];
        snprintf(cmd, sizeof(cmd), "id %s", username);
        execl("/bin/sh", "sh", "-c", cmd, NULL);
        perror("execl"); // If execl fails
        exit(127);
    } else if (pid > 0) {
        // Parent process
        waitpid(pid, &status, 0);
        if (WIFEXITED(status)) {
            return WEXITSTATUS(status);
        } else {
            return -1; // Indicates an error
        }
    } else {
        perror("fork"); // Fork failed
        return -1;
    }
}

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <username>\n", argv[0]);
        exit(EXIT_FAILURE);
    }

    char *username = argv[1];
    int status = run_command(username);

    if (status == 0) {
        printf("Command succeeded.\n");
    } else {
        printf("Command failed with exit status %d.\n", status);
    }

    return EXIT_SUCCESS;
}