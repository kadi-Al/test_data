#include <stdio.h>
#include <stdlib.h>

int main() {
    int input;
    printf("Enter the number of elements you want to allocate (each 4 bytes): ");
    scanf("%d", &input);

    // Allocate memory for 'input' integers, each integer is 4 bytes
    int *array = malloc(input * sizeof(int));

    if (array == NULL) {
        printf("Memory allocation failed! Exiting the program.\n");
        return 1;
    } else {
        printf("Successfully allocated %d elements, each of size 4 bytes. Total memory: %zu bytes\n", input, input * sizeof(int));
    }

    // Optionally free the allocated memory
    free(array);

    return 0;
}