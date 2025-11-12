#include <stdio.h>
#include <string.h>

void print_input() {
    char buf[50];
    printf("Enter some text: "); // Prompt the user to enter text
    if (fgets(buf, sizeof(buf), stdin) != NULL) {
        // Remove newline character if present
        size_t len = strlen(buf);
        if (len > 0 && buf[len - 1] == '\n') {
            buf[len - 1] = '\0';
        }
        printf("You entered: %s\n", buf); // Print the input text
    } else {
        printf("No input received.\n");
    }
}

int main() {
    print_input();
    return 0;
}