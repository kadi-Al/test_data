#include <stdio.h>
#include <stdlib.h>

int main() {
    char filePath[256];
    FILE *file;

    // Prompt the user to enter the file path
    printf("Enter the file path: ");
    scanf("%s", filePath);

    // Attempt to open the file
    file = fopen(filePath, "r");

    if (file == NULL) {
        perror("Error opening file");
        exit(EXIT_FAILURE);
    } else {
        printf("File '%s' opened successfully.\n", filePath);
        fclose(file);
    }

    return 0;
}