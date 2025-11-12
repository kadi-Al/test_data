#include <stdio.h>

void write_to_file(const char *filename, int data) {
    FILE *file = fopen(filename, "w"); // Open file in write mode
    if (file == NULL) {
        perror("Error opening file");
        return;
    }

    fprintf(file, "The value is: %d\n", data); // Assume this always succeeds

    fclose(file); // Close the file
}

int main() {
    write_to_file("output.txt", 42); // Call function with example data
    return 0;
}