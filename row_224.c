#include <stdio.h>

int main() {
    char userInput[100]; // Buffer to store user input

    printf("Enter a line of text: ");
    gets(userInput); // Use gets to read the input

    printf("You entered: %s\n", userInput);

    return 0;
}
#include <stdio.h>

int main() {
    char userInput[100]; // Buffer to store user input

    printf("Enter a line of text: ");
    fgets(userInput, sizeof(userInput), stdin); // Use fgets for safer input

    // Remove newline character if present (fgets includes it)
    size_t len = strlen(userInput);
    if (len > 0 && userInput[len - 1] == '\n') {
        userInput[len - 1] = '\0';
    }

    printf("You entered: %s\n", userInput);

    return 0;
}