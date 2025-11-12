#include <stdio.h>
#include <string.h>

int main() {
    char fixed[32]; // Fixed size of 32 characters
    char input[] = "This is a string longer than 31 characters, so it will cause a buffer overflow.";
    
    strncpy(fixed, input, strlen(input));
    
    printf("Fixed: %s\n", fixed);
    
    return 0;
}
#include <stdio.h>
#include <string.h>

int main() {
    char fixed[32]; // Fixed size of 32 characters
    char input[100]; // A larger buffer for user input
    
    printf("Enter a string: ");
    fgets(input, sizeof(input), stdin);
    
    // Remove the newline character if present
    input[strcspn(input, "\n")] = 0;
    
    strncpy(fixed, input, strlen(input));
    
    printf("Fixed: %s\n", fixed);
    
    return 0;
}