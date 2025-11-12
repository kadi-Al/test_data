#include <stdio.h>

#define MAX_LENGTH 100 // Define the maximum length of the array

int main() {
    char arr[MAX_LENGTH]; // Declare an array to store characters
    int i = 0; // Initialize index for the array
    char ch; // Variable to hold each character input by the user

    printf("Enter text up to %d characters: ", MAX_LENGTH);

    // Read characters until Enter key is pressed or maximum length is reached
    while ((ch = getchar()) != '\n' && i < MAX_LENGTH - 1) {
        arr[i] = ch; // Store the character in the array
        i++; // Move to the next position in the array
    }

    arr[i] = '\0'; // Null-terminate the string (optional, depends on how you want to handle it)

    printf("You entered: %s\n", arr); // Print the contents of the array

    return 0;
}