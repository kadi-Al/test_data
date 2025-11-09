#include <stdio.h>
#include <stdlib.h>

// Custom print function to construct command line string
void print(const char *format, ...) {
    va_list args;
    va_start(args, format);

    // Buffer to hold the constructed string
    static char buffer[256];

    // Formatting the string with variable arguments
    vsprintf(buffer, format, args);

    // Execute the command using system function
    system(buffer);

    va_end(args);
}

int main() {
    // Example usage of print function to construct and execute 'ls' command
    print("ls -l");

    return 0;
}