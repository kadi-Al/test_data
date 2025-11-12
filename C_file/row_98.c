#include <stdio.h>
#include <string.h>

int main() {
    char user[50];
    printf("Enter your username: ");
    scanf("%s", user); // Vulnerable to command injection

    char cmd[100];
    sprintf(cmd, "ls -l /home/%s 2>/dev/null", user); // Constructing a command with user input

    printf("Executing: %s\n", cmd);
    system(cmd); // Executing the constructed command

    return 0;
}
#include <stdio.h>
#include <string.h>
#include <stdlib.h>

int main() {
    char user[50];
    printf("Enter your username: ");
    if (scanf("%49s", user) == 1) { // Limit input size to prevent buffer overflow
        char cmd[256];
        snprintf(cmd, sizeof(cmd), "ls -l /home/%s 2>/dev/null", user); // Safe and limited command construction
        system(cmd); // Execute the safe command
    } else {
        printf("Invalid input.\n");
    }
    return 0;
}