#include <stdio.h>
#include <stdlib.h>

int main() {
    char filename[256]; // Buffer to store the filename entered by user
    FILE *file;

    // Prompt the user for the filename
    printf("Enter the filename: ");
    scanf("%s", filename);

    // Attempt to open the file
    file = fopen(filename, "r"); // Open the file in read mode

    // Check if the file was successfully opened
    if (file == NULL) {
        perror("Error opening file"); // Print an error message using perror
        return EXIT_FAILURE; // Exit with a failure status
    }

    // If the file is opened successfully, close it and exit
    fclose(file);
    printf("File '%s' opened successfully.\n", filename);

    return EXIT_SUCCESS; // Exit with success status
}