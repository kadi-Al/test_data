#include <stdio.h>
#include <string.h>

int main() {
    char buffer[8];  // Array to store the input

    printf("Enter a string (max 7 characters): ");
    gets(buffer);  // Use of gets(), which is unsafe due to lack of bounds checking

    printf("You entered: %s\n", buffer);

    return 0;
}
fgets(buffer, sizeof(buffer), stdin);
#include <stdio.h>
#include <string.h>

int main() {
    char buffer[8];  // Array to store the input

    printf("Enter a string (max 7 characters): ");
    fgets(buffer, sizeof(buffer), stdin);
    buffer[strcspn(buffer, "\n")] = '\0';  // Remove newline character if present

    printf("You entered: %s\n", buffer);

    return 0;
}