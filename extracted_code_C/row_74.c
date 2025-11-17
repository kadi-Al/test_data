#include <stdio.h>

int main() {
    char *p = NULL; // Initialize p to NULL (uninitialized pointer)
    printf("%c", *p); // Dereference the uninitialized pointer and print its value
    return 0;
}
#include <stdio.h>

int main() {
    char ch = 'A'; // Define a character variable
    char *p = &ch; // Initialize p to point to ch
    printf("%c", *p); // Print the value pointed by p
    return 0;
}