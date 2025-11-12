#include <stdio.h>
#include <stdlib.h> // for exit() function

int main(int argc, char *argv[]) {
    if (argc != 2) {
        fprintf(stderr, "Usage: %s <filename>\n", argv[0]);
        exit(1);
    }

    const char *filename = argv[1];

    // Attempt to open the file for reading and writing
    FILE *file = fopen(filename, "r");
    if (file == NULL) {
        fprintf(stderr, "File '%s' does not exist. Creating it...\n", filename);
        // Create a new empty file by opening in write mode
        file = fopen(filename, "w");
        if (file == NULL) {
            perror("Error creating file");
            exit(1);
        }
        fclose(file); // Close the newly created file
    } else {
        // File exists, proceed to delete it
        fclose(file); // Close the file before deleting
        if (remove(filename) != 0) {
            perror("Error deleting file");
            exit(1);
        }
        printf("File '%s' deleted successfully.\n", filename);
    }

    return 0;
}
bash
gcc -o check_delete check_delete.c
bash
./check_delete myfile.txt