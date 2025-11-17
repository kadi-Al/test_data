#include <stdio.h>

int main() {
    // Define the format string containing the placeholder for the password
    const char *format = "Using password: %s\n";
    
    // Define the password (in this case, a simple string)
    const char *password = "examplePassword123";
    
    // Use printf with the format string and the password as arguments
    printf(format, password);
    
    return 0;
}
Using password: examplePassword123