execl(user_program, user_program, NULL);
#include <unistd.h>  // For execl
#include <stdio.h>   // For printf

int main() {
    char *args[] = {"ls", "-l", NULL};  // Example arguments for the "ls -l" command

    printf("Executing 'ls -l'\n");
    execl("/bin/ls", "ls", "-l", NULL);  // Replace this process with "ls -l"

    // If execl fails, perror will print an error message
    perror("execl");  // This line is rarely reached because execl usually does not return

    return 0;
}