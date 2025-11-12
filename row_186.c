#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Function to join a user-provided path with a base directory
char* joinPath(const char* baseDir, const char* relativePath) {
    // Determine the length of the base directory and relative path
    size_t baseLen = strlen(baseDir);
    size_t relLen = strlen(relativePath);
    
    // Allocate memory for the combined path
    char* fullPath = (char*)malloc(baseLen + relLen + 2); // +1 for '/' and +1 for null terminator
    
    if (!fullPath) {
        perror("Failed to allocate memory");
        exit(EXIT_FAILURE);
    }
    
    // Copy the base directory path
    strcpy(fullPath, baseDir);
    
    // Append a '/' if necessary
    if (baseLen > 0 && fullPath[baseLen - 1] != '/') {
        fullPath[baseLen] = '/';
        baseLen++;
    }
    
    // Copy the relative path
    strcpy(fullPath + baseLen, relativePath);
    
    return fullPath;
}

// Function to read a file and print its contents
void readFile(const char* baseDir, const char* fileName) {
    char* fullPath = joinPath(baseDir, fileName);
    
    // Open the file
    FILE* file = fopen(fullPath, "r");
    if (!file) {
        perror("Failed to open file");
        free(fullPath);
        exit(EXIT_FAILURE);
    }
    
    // Get the size of the file
    fseek(file, 0, SEEK_END);
    long fileSize = ftell(file);
    rewind(file);
    
    // Allocate memory to read the file content
    char* buffer = (char*)malloc(fileSize + 1);
    if (!buffer) {
        perror("Failed to allocate memory");
        fclose(file);
        free(fullPath);
        exit(EXIT_FAILURE);
    }
    
    // Read the file content
    size_t bytesRead = fread(buffer, 1, fileSize, file);
    buffer[bytesRead] = '\0'; // Null-terminate the string
    
    // Print the file content
    printf("File contents:\n%s\n", buffer);
    
    // Clean up
    free(buffer);
    fclose(file);
    free(fullPath);
}

int main() {
    char baseDir[256];
    char fileName[256];
    
    printf("Enter the base directory: ");
    if (scanf("%255s", baseDir) != 1) {
        fprintf(stderr, "Failed to read base directory\n");
        return EXIT_FAILURE;
    }
    
    printf("Enter the file name: ");
    if (scanf("%255s", fileName) != 1) {
        fprintf(stderr, "Failed to read file name\n");
        return EXIT_FAILURE;
    }
    
    readFile(baseDir, fileName);
    
    return EXIT_SUCCESS;
}