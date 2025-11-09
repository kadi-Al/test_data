#include <stdio.h>
#include <stdlib.h>

#define BUFFER_SIZE 1024

int main() {
    // Allocate memory for the buffer
    char *buf = (char *)malloc(BUFFER_SIZE * sizeof(char));
    if (buf == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    int i = 0;
    while ((i < BUFFER_SIZE - 1) && (scanf("%c", &buf[i]) != EOF)) {
        if (buf[i] == '\n') { // Stop reading if newline is encountered
            break;
        }
        i++;
    }
    buf[i] = '\0'; // Null-terminate the string

    printf("Read characters: %s\n", buf);

    // Free allocated memory
    free(buf);

    return 0;
}