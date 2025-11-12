#include <stdio.h>
#include <stdlib.h>

void* allocateBuffer(size_t size) {
    void* buffer = malloc(size);
    if (buffer == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return NULL;
    }
    return buffer;
}

void freeBuffer(void* buffer) {
    if (buffer != NULL) {
        free(buffer);
    }
}

int main() {
    size_t userSize;
    printf("Enter the size of the memory block you want to allocate: ");
    scanf("%zu", &userSize);

    void* allocatedBuffer = allocateBuffer(userSize);
    if (allocatedBuffer != NULL) {
        printf("Memory allocated successfully at address %p\n", allocatedBuffer);
    } else {
        printf("Failed to allocate memory.\n");
    }

    // Free the buffer when done (optional, as it depends on how you manage memory in your application)
    freeBuffer(allocatedBuffer);

    return 0;
}