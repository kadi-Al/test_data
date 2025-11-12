#include <stdio.h>
#include <string.h>

int main() {
    char input[] = "Hello, World!"; // Example input string
    int size = strlen(input); // Get the length of the input string
    char buf[size]; // Allocate an array of the same size as the input string

    // Copy the content from input to buf
    for (int i = 0; i < size; i++) {
        buf[i] = input[i];
    }

    // Add null terminator at the end
    buf[size] = '\0';

    // Print the string in C style
    printf("%s\n", buf);

    return 0;
}