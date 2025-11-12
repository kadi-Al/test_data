#include <stdio.h>
#include <stdlib.h>

void log_user_input(const char *log_filename) {
    FILE *log_file = fopen(log_filename, "w"); // Open the file in write mode
    if (log_file == NULL) {
        perror("Error opening log file");
        return;
    }

    int c;
    while ((c = getchar()) != EOF) {
        fputc(c, log_file); // Write the character to the log file
    }

    fclose(log_file); // Close the log file
}

int main() {
    const char *log_filename = "user_input.log";
    printf("Enter your input (type Ctrl+D on Unix/Linux, Ctrl+Z on Windows to finish):\n");
    log_user_input(log_filename);
    return 0;
}