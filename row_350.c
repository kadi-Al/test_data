#include <stdio.h>

// Function to get a value from an array based on the given index
int getValueFromArray(int arr[], int size, int index) {
    if (index >= 0 && index < size) {
        return arr[index];
    } else {
        printf("Index is out of bounds!\n");
        return -1; // Return an error value if the index is invalid
    }
}

int main() {
    int array[] = {10, 20, 30, 40, 50};
    int size = sizeof(array) / sizeof(array[0]);
    int index;

    printf("Enter the index to get value from the array: ");
    scanf("%d", &index);

    int value = getValueFromArray(array, size, index);

    if (value != -1) {
        printf("The value at index %d is: %d\n", index, value);
    }

    return 0;
}