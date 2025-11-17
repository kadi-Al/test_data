#include <string.h>

void partial_copy_to_buffer(char *dest, const char *src, size_t num_bytes) {
    if (num_bytes > sizeof(dest)) {
        // If the number of bytes to copy is greater than the buffer size, cap it at the buffer size.
        num_bytes = sizeof(dest);
    }
    memcpy(dest, src, num_bytes);
}
#include <stdio.h>
#include <string.h>

int main() {
    char buffer[10] = {0}; // Initialize with zeros to show any garbage values clearly.
    const char *source = "Hello, World!";
    
    partial_copy_to_buffer(buffer, source, 5); // Copy first 5 bytes of source into buffer.
    
    printf("Buffer after copy: %s\n", buffer); // This might print "Hello" or some garbage values depending on initial state and platform.
    
    return 0;
}