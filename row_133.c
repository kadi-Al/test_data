#include <stdio.h>
#include <string.h> // For memset function

int main(int argc, char *argv[]) {
    if (argc < 2) {
        printf("Usage: %s <input>\n", argv[0]);
        return 1;
    }

    // Create a buffer of size 10 and initialize it with zeros
    char buffer[10];
    memset(buffer, 0, sizeof(buffer)); // This is used to clear the buffer

    // Copy up to 9 characters from argv[1] to buffer
    strncpy(buffer, argv[1], sizeof(buffer) - 1);

    // Print the contents of the buffer
    printf("Buffer: %s\n", buffer);

    return 0;
}
sh
gcc -o copy_input copy_input.c
sh
./copy_input "Your input here"
Buffer: hello