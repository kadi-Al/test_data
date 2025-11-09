#include <stdio.h>
#include <stdlib.h>

int main() {
    FILE *file;
    char path[] = "/safe/your_file.txt"; // Replace with the actual file path in /safe/ directory
    char buffer[256];

    // Open the file in read mode
    file = fopen(path, "r");
    if (file == NULL) {
        perror("Error opening file");
        return EXIT_FAILURE;
    }

    // Read and print the contents of the file
    while (fgets(buffer, sizeof(buffer), file) != NULL) {
        printf("%s", buffer);
    }

    // Close the file
    fclose(file);

    return EXIT_SUCCESS;
}
sh
gcc -o readfile readfile.c
sh
./readfile