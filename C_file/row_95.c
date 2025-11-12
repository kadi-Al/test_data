#include <stdio.h>
#include <string.h>

void vulnerable_function() {
    char buf[8];  // Buffer of size 8
    strcpy(buf, "123456789\\");  // Attempting to copy more than the buffer can hold
}

int main() {
    vulnerable_function();
    printf("Buffer overflow example completed.\n");
    return 0;
}