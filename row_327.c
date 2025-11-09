#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define SAFE_DIR "/safe/"
#define MAX_FILE_NAME 256

int main() {
    char file_name[MAX_FILE_NAME];
    FILE *file;

    // Prompt the user to enter the file name
    printf("Enter the filename to read from %s: ", SAFE_DIR);
    scanf("%s", file_name);

    // Construct the full path of the file
    char full_path[MAX_FILE_NAME + strlen(SAFE_DIR)];
    strcpy(full_path, SAFE_DIR);
    strcat(full_path, file_name);

    // Open the file in read mode
    file = fopen(full_path, "r");
    if (file == NULL) {
        perror("Error opening file");
        return EXIT_FAILURE;
    }

    // Read and print the contents of the file
    char ch;
    while ((ch = fgetc(file)) != EOF) {
        putchar(ch);
    }

    // Close the file
    fclose(file);

    return EXIT_SUCCESS;
}