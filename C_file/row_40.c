int *element = arr + (index * sizeof(int));
int *element = arr + index;  // Correct approach
int *element = &arr[0] + index;  // Correct approach using the base address
#include <stdio.h>

int main() {
    int arr[] = {10, 20, 30, 40, 50};
    int index = 2;

    // Correct way to access the element using array indexing:
    int correct_element = arr[index];
    printf("Correct element at index %d is %d\n", index, correct_element);

    // Incorrect way to access the element using pointer arithmetic:
    int *wrong_element = arr + (index * sizeof(int));  // Incorrect!
    printf("Wrongly accessed element at index %d is %d\n", index, *wrong_element);

    return 0;
}