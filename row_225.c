#include <stdio.h>

int main() {
    char user_input[100]; // Buffer to store user input

    printf("Enter some text: ");
    fgets(user_input, sizeof(user_input), stdin); // Read the input from the user

    printf("You entered: %s", user_input); // Print the user's input

    return 0;
}