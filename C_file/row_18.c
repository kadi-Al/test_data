int index = 0;
index--; // This will cause underflow
array[index] = value; // Accessing an invalid index
int array[] = {10, 20, 30, 40}; // Example array with four elements
int index = sizeof(array) / sizeof(array[0]) - 1; // Last valid index
index--; // Decrement to get the previous index
if (index >= 0) { // Ensure index is non-negative
    array[index] = value; // Accessing a valid index
} else {
    printf("Index out of bounds!\n");
}
int array[] = {10, 20, 30, 40}; // Example array with four elements
int value = 99; // The value to be assigned
for (int i = sizeof(array) / sizeof(array[0]) - 1; i >= 0; i--) {
    if (i == 0) { // If it's the first element, assign the value and stop
        array[i] = value;
        break;
    }
}