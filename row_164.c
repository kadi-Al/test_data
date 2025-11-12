#include <stdio.h>

// Define a simple structure
typedef struct {
    int data[5];
} MyStruct;

int main() {
    // Create an instance of the structure
    MyStruct myStruct = {{1, 2, 3, 4, 5}};

    // Obtain a pointer to the structure and try to access elements using pointer arithmetic
    MyStruct *ptr = &myStruct;

    // Attempting to access elements using incorrect pointer arithmetic
    for (int i = 0; i < 5; i++) {
        printf("Element %d: %d\n", i, ptr->data[i]); // This line is actually correct but the rest is not
    }

    return 0;
}
#include <stdio.h>

// Define a simple structure
typedef struct {
    int data[5];
} MyStruct;

int main() {
    // Create an instance of the structure
    MyStruct myStruct = {{1, 2, 3, 4, 5}};

    // Access elements directly using array indexing syntax
    for (int i = 0; i < 5; i++) {
        printf("Element %d: %d\n", i, myStruct.data[i]);
    }

    return 0;
}