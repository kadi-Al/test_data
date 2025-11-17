#include <stdio.h>

int main() {
    FILE *file;
    char password[] = "your_password_here";

    // Open the file in write mode
    file = fopen("passwords.txt", "w");
    if (file == NULL) {
        printf("Error opening file!\n");
        return 1;
    }

    // Write the password to the file
    fprintf(file, "%s\n", password);

    // Close the file
    fclose(file);

    return 0;
}