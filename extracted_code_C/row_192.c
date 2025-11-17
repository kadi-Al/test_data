#include <stdio.h>

// Function to add an integer to a pointer
void* add_int_to_pointer(void* ptr, int offset) {
    return (char*)ptr + offset;
}

int main() {
    int arr[5] = {10, 20, 30, 40, 50};
    int *ptr = arr; // Pointing to the first element of the array
    int offset = 2;
    
    // Adding the offset to the pointer
    void* new_ptr = add_int_to_pointer(ptr, offset);
    
    // Casting back to int* and printing the value
    printf("The value at new position is: %d\n", *(int*)new_ptr);
    
    return 0;
}