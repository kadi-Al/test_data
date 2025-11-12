#include <stdio.h>
#define ARRAY_SIZE 10 // Define a size for the array

int main() {
    int arr[ARRAY_SIZE]; // Declare an array with predefined size
    int index;

    // Initialize the array elements (optional, you can also leave them uninitialized)
    for(int i = 0; i < ARRAY_SIZE; i++) {
        arr[i] = i * 10; // Example initialization
    }

    // Prompt user for an index
    printf("Enter the index to access in the array: ");
    scanf("%d", &index);

    // Access the element at the given index
    if(index >= 0 && index < ARRAY_SIZE) {
        printf("Element at index %d is %d\n", index, arr[index]);
    } else {
        printf("Index out of bounds! The array size is only %d.\n", ARRAY_SIZE);
    }

    return 0;
}