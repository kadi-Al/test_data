#include <stdio.h>
#include <stdlib.h>

int main() {
    const char *username = "your_username"; // Replace with the actual username
    FILE *output;

    // Execute command using system() and capture output in a file pointer
    int result = system("getent passwd your_username > output.txt");

    if (result == 0) {
        // Open the file for reading
        output = fopen("output.txt", "r");
        if (output != NULL) {
            char ch;
            while ((ch = fgetc(output)) != EOF) {
                putchar(ch);
            }
            fclose(output);
        } else {
            perror("Error opening file");
        }
    } else {
        fprintf(stderr, "Command execution failed\n");
    }

    return 0;
}
#include <stdio.h>
#include <stdlib.h>

int main() {
    const char *username = "your_username"; // Replace with the actual username
    FILE *output;

    // Execute command using popen() and capture output in a file pointer
    output = popen(("getent passwd " + std::string(username)).c_str(), "w");

    if (output != NULL) {
        pclose(output);

        // Open the file for reading
        output = fopen("output.txt", "r");
        if (output != NULL) {
            char ch;
            while ((ch = fgetc(output)) != EOF) {
                putchar(ch);
            }
            fclose(output);
        } else {
            perror("Error opening file");
        }
    } else {
        fprintf(stderr, "Command execution failed\n");
    }

    return 0;
}