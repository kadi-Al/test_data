#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Define a maximum length for the filename and file content
#define MAX_FILENAME_LENGTH 256
#define MAX_FILE_CONTENT_LENGTH 1024

// Function to read file content safely, assuming safe directory is "/safe/"
char* read_file(const char* filename) {
    // Check if the filename is valid and does not contain path traversal characters
    if (strstr(filename, "..") != NULL || strstr(filename, "/") != NULL) {
        printf("Invalid filename or path.\n");
        return NULL;
    }

    char safe_directory[] = "/safe/";
    char full_path[MAX_FILENAME_LENGTH + sizeof(safe_directory)];
    snprintf(full_path, sizeof(full_path), "%s%s", safe_directory, filename);

    FILE* file = fopen(full_path, "r");
    if (!file) {
        perror("Error opening file");
        return NULL;
    }

    // Determine the size of the file
    fseek(file, 0, SEEK_END);
    long file_size = ftell(file);
    rewind(file);

    if (file_size > MAX_FILE_CONTENT_LENGTH) {
        printf("File too large to read.\n");
        fclose(file);
        return NULL;
    }

    // Allocate memory for the file content
    char* buffer = (char*)malloc((size_t)file_size + 1);
    if (!buffer) {
        perror("Memory allocation failed");
        fclose(file);
        return NULL;
    }

    // Read the file into the buffer
    size_t bytes_read = fread(buffer, 1, (size_t)file_size, file);
    if (bytes_read != (size_t)file_size) {
        perror("Error reading file");
        free(buffer);
        buffer = NULL;
    } else {
        buffer[file_size] = '\0'; // Null-terminate the string
    }

    fclose(file);
    return buffer;
}

int main(int argc, char* argv[]) {
    if (argc != 2) {
        printf("Usage: %s <filename>\n", argv[0]);
        return EXIT_FAILURE;
    }

    char* file_content = read_file(argv[1]);
    if (!file_content) {
        return EXIT_FAILURE;
    }

    // For demonstration, print the content of the file
    printf("File content:\n%s\n", file_content);

    // Free the allocated memory for the file content
    free(file_content);

    return EXIT_SUCCESS;
}