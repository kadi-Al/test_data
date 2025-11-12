#include <stdio.h>
#include <stdlib.h>

int main() {
    int N;
    printf("Enter the number of integers: ");
    scanf("%d", &N);

    // Allocate memory for N integers using malloc(N)
    int *array = (int *)malloc(N * sizeof(int));
    if (array == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Initialize the array with some values
    for (int i = 0; i < N; i++) {
        array[i] = i + 1; // Example: initialize to 1, 2, 3, ..., N
    }

    // Print the allocated memory contents
    printf("The allocated array contains:\n");
    for (int i = 0; i < N; i++) {
        printf("%d ", array[i]);
    }
    printf("\n");

    // Free the allocated memory
    free(array);

    return 0;
}
sh
gcc -o allocate_memory allocate_memory.c
./allocate_memory