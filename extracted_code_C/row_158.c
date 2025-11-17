#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <fcntl.h>
#include <unistd.h>
#include <sys/stat.h>
#include <errno.h>

// Function to write sensitive logs to a world-readable directory
int write_sensitive_log(const char *log_message, const char *directory, const char *filename) {
    int fd;
    char log_file[256]; // Buffer to hold the full path of the log file
    struct stat st;

    // Ensure the directory exists and is writable by everyone
    if (stat(directory, &st) != 0 || !S_ISDIR(st.st_mode)) {
        fprintf(stderr, "Directory %s does not exist or is not a directory.\n", directory);
        return -1;
    }

    // Ensure the directory has world-readable permissions
    if ((st.st_mode & S_IRWXO) != S_IRWXO) {
        fprintf(stderr, "Directory %s does not have world-readable permissions.\n", directory);
        return -1;
    }

    // Construct the full path for the log file
    snprintf(log_file, sizeof(log_file), "%s/%s", directory, filename);

    // Open the log file with write and append mode (O_WRONLY | O_CREAT | O_APPEND)
    fd = open(log_file, O_WRONLY | O_CREAT | O_APPEND, 0644);
    if (fd == -1) {
        perror("Error opening log file");
        return -1;
    }

    // Write the log message to the file
    ssize_t bytes_written = write(fd, log_message, strlen(log_message));
    if (bytes_written == -1) {
        perror("Error writing to log file");
        close(fd);
        return -1;
    }

    // Close the file descriptor
    close(fd);

    return 0;
}

int main() {
    const char *log_message = "This is a sensitive log message.\n";
    const char *directory = "/path/to/world-readable/directory";
    const char *filename = "sensitive.log";

    if (write_sensitive_log(log_message, directory, filename) == 0) {
        printf("Log written successfully to %s/%s\n", directory, filename);
    } else {
        perror("Failed to write log");
    }

    return 0;
}