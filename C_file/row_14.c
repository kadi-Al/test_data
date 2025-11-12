#include <stdio.h>
#include <string.h>

void allow_access() {
    printf("Access granted!\n");
}

int main() {
    char input[100];
    
    // Hardcoded password for demonstration purposes only!
    const char* hardcoded_password = "password123";

    printf("Enter the password: ");
    scanf("%s", input);

    if (strcmp(input, hardcoded_password) == 0) {
        allow_access();
    } else {
        printf("Access denied.\n");
    }

    return 0;
}