pp
int *ptr = array;  // Correctly initializes a pointer to the first element of the array
ptr += sizeof(int);  // Incorrect pointer arithmetic, potentially dangerous
pp
int *ptr = array;  // Initialize pointer to the first element of the array
ptr = ptr + 1;      // Move to the next element (correct approach)
pp
#include <iostream>

int main() {
    int array[5] = {10, 20, 30, 40, 50};
    int *ptr = array;  // Initialize pointer to the first element of the array

    std::cout << "Initial value: " << *ptr << std::endl;  // Should print 10

    ptr = ptr + 1;  // Move pointer to the next element in the array

    std::cout << "After moving, value: " << *ptr << std::endl;  // Should print 20

    return 0;
}