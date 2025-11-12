#include <stdio.h>
#include <stdlib.h> // For atoi function

int main() {
    char userInput[100]; // Array to store the input string
    int number;

    printf("Enter a numeric string: ");
    fgets(userInput, sizeof(userInput), stdin);

    // Remove newline character if present (for safety)
    size_t len = strlen(userInput);
    if (len > 0 && userInput[len - 1] == '\n') {
        userInput[len - 1] = '\0';
    }

    // Convert the string to an integer using atoi
    number = atoi(userInput);

    printf("You entered: %d\n", number);

    return 0;
}