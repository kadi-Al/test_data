#include <stdio.h>

// Function to read a line of text using scanf without specifying length limit
void readLine(char *buffer, int maxLength) {
    if (buffer == NULL || maxLength <= 0) {
        return; // Return immediately if the buffer is invalid or size is non-positive
    }

    printf("Enter a line of text: ");
    if (scanf("%s", buffer) != 1) {
        buffer[0] = '\0'; // Clear the buffer if input fails
    } else {
        int currentLength = strlen(buffer);
        if (currentLength >= maxLength) {
            buffer[maxLength - 1] = '\0'; // Truncate the string to fit within maxLength
        }
    }
}

int main() {
    const int MAX_LENGTH = 100; // Define a maximum length for the input buffer
    char userInput[MAX_LENGTH];   // Declare the buffer

    readLine(userInput, MAX_LENGTH); // Call the function to read input into the buffer

    printf("You entered: %s\n", userInput); // Print the contents of the buffer

    return 0;
}