#include <stdio.h>
#include <stdlib.h>

int main() {
    char filename[100];

    // Prompt the user for the filename
    printf("Enter the filename: ");
    scanf("%s", filename);

    // Attempt to open the file
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        perror("Error opening file");
        return EXIT_FAILURE;
    }

    // File opened successfully, you can now read from it or perform other operations
    printf("File '%s' opened successfully!\n", filename);

    // Close the file
    fclose(file);

    return EXIT_SUCCESS;
}