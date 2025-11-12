#include <stdio.h>

int main() {
    int array[] = {10, 20, 30, 40};
    int *ptr = array; // Initialize pointer to the first element of the array

    printf("Initial value: %d\n", *ptr); // Dereference the pointer and print its value

    // Increment the pointer by the size of an int (which is typically 4 bytes)
    ptr++;

    printf("Value after incrementing by sizeof(int): %d\n", *ptr); // Should point to the next element, which is 20

    // Now, let's demonstrate with a char array to see the byte-wise increment
    char chars[] = {'a', 'b', 'c', 'd'};
    char *char_ptr = chars;

    printf("Initial value: %c\n", *char_ptr); // Dereference the pointer and print its value

    // Increment the pointer by the size of a char (which is 1 byte)
    char_ptr++;

    printf("Value after incrementing by sizeof(char): %c\n", *char_ptr); // Should point to the next element, which is 'b'

    return 0;
}