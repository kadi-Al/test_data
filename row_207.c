#include <stdio.h>

int main() {
    // Define the file name you want to open
    char *filename = "nonexistentfile.txt";

    // Open the file using fopen
    FILE *file = fopen(filename, "r");

    // Print a message indicating that we tried to open the file
    if (file == NULL) {
        printf("Failed to open %s.\n", filename);
    } else {
        printf("Successfully opened %s.\n", filename);
        fclose(file);  // Close the file after use
    }

    return 0;
}