#include <stdio.h>
#include <string.h>

char comments[5][100]; // Array to store comments, each comment can have up to 99 characters
int index = 0; // Index for the array where we will store user input

void add_comment(char *user_input) {
    strcpy(comments[index], user_input); // Copy user input into the array without sanitizing it
    index++;
}

void print_comments() {
    for (int i = 0; i < index; i++) {
        printf("%s\n", comments[i]); // Print out all stored comments
    }
}

int main() {
    char user_input[100];
    
    printf("Enter your comment: ");
    fgets(user_input, 100, stdin); // Read user input from stdin

    add_comment(user_input); // Add the user input to the comments array

    print_comments(); // Print all stored comments including the one added by the user
    
    return 0;
}
strncpy(comments[index], user_input, sizeof(comments[index]) - 1); // Safely copies with length check
comments[index][sizeof(comments[index]) - 1] = '\0'; // Ensures null-termination if not already done by strncpy