#include <stdio.h>

int main() {
    FILE *config = fopen("config.txt", "w"); // Open or create the file for writing
    if (config == NULL) {
        perror("Error opening file");
        return 1;
    }
    fprintf(config, "db_pass=secret123\n"); // Write to the file
    fclose(config); // Close the file after writing
    return 0;
}