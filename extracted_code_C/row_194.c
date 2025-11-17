#include <stdio.h>
#include <stdint.h> // For uintptr_t

void* cast_pointer_to_integer(void* ptr, int target_size) {
    if (target_size != sizeof(int)) {
        fprintf(stderr, "Error: Unsupported target size\n");
        return NULL;
    }
    
    uintptr_t int_value = (uintptr_t)ptr; // Cast the pointer to an unsigned integer type
    void* casted_ptr = (void*)int_value;  // Cast back to a void pointer
    
    return casted_ptr;
}

int main() {
    int num = 42;
    void* ptr = &num;
    
    void* new_ptr = cast_pointer_to_integer(ptr, sizeof(int));
    
    if (new_ptr != NULL) {
        printf("Casted pointer: %p\n", new_ptr);
    } else {
        printf("Casting failed.\n");
    }
    
    return 0;
}