#include <stdio.h>

int main() {
    int i = 0; // Initialize i, but it won't change in this specific loop

    do {
        printf("The value of i is currently: %d\n", i);
        printf("Type 'continue' to continue the loop or any other input to break:\n");
        char input[10]; // Buffer to read user input
        scanf("%s", input);

        if (strcmp(input, "continue") == 0) {
            // If the user types 'continue', the loop continues
            continue;
        } else {
            // Any other input will break the loop
            printf("Loop breaking due to user input.\n");
            break;
        }
    } while (1); // Infinite loop condition that is always true

    return 0;
}