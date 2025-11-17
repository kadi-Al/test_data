#include <stdio.h>
#include <string.h>

void memcpy_overflow(void* buffer, const void* data, size_t data_len) {
    // No size check for destination buffer size
    memcpy(buffer, data, data_len);
}

int main() {
    char source[] = "Hello, World!";
    char buffer[5];  // Destination buffer is too small to hold the entire source string

    printf("Before memcpy: %s\n", buffer);
    memcpy_overflow(buffer, source, sizeof(source));
    printf("After memcpy: %s\n", buffer);

    return 0;
}