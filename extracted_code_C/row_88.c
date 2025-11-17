#include <stdio.h>
#include <string.h>

char file[100];

void getFile() {
    printf("Enter the filename: ");
    scanf("%99s", file); // Vulnerable part: No bounds checking
}

int main() {
    getFile();
    
    // Example of path traversal vulnerability
    char *pos = strchr(file, '.');
    if (pos != NULL) {
        *pos = '\0'; // Temporarily null-terminate the string at the file extension
        printf("Possible path traversal attempt detected: %s\n", file);
        *pos = '.'; // Restore the original string after detection
    }
    
    printf("You entered: %s\n", file);
    return 0;
}
#include <stdio.h>
#include <string.h>

char file[100];

void getFile(char *buffer, size_t size) {
    printf("Enter the filename: ");
    if (fgets(buffer, size, stdin) != NULL) {
        buffer[strcspn(buffer, "\n")] = '\0'; // Remove newline character if present
    }
}

int main() {
    getFile(file, sizeof(file));
    
    char *pos = strchr(file, '.');
    if (pos != NULL) {
        *pos = '\0'; // Temporarily null-terminate the string at the file extension
        printf("Possible path traversal attempt detected: %s\n", file);
        *pos = '.'; // Restore the original string after detection
    }
    
    printf("You entered: %s\n", file);
    return 0;
}