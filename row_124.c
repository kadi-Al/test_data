#include <stdio.h>

int main() {
    // Declare an integer array of size 5 without initializing it
    int arr[5];

    // Print the contents of the array
    printf("Contents of the array:\n");
    for (int i = 0; i < 5; ++i) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}