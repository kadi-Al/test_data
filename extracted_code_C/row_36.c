#include <stdio.h>
#include <stdlib.h>
#include <string.h>

void vulnerable_printf(char *buffer) {
    printf(buffer);  // Vulnerable to format string attack
}

int main() {
    char buffer[100];

    // Example: Prompt user for input
    printf("Enter a format string: ");
    fgets(buffer, sizeof(buffer), stdin);

    // Remove the newline character if present
    buffer[strcspn(buffer, "\n")] = '\0';

    vulnerable_printf(buffer);

    return 0;
}
sh
$ ./vulnerable_printf 
Enter a format string: %s
Hello, World!