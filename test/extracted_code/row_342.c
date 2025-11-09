#include <stdio.h>
#include <stdlib.h>

// Custom print function to construct the command string
void print(const char *format, ...) {
    va_list args;
    va_start(args, format);
    
    // Buffer to hold the constructed string
    char buffer[256];
    vsnprintf(buffer, sizeof(buffer), format, args);
    
    va_end(args);
    
    // Execute the command using system function
    system(buffer);
}

int main() {
    // Example usage of the print function to construct and execute a command
    print("ls -l");
    
    return 0;
}