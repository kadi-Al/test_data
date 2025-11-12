#include <stdio.h>

int main() {
    int N; // Define the number of elements you want in your array
    printf("Enter the size of the array: ");
    scanf("%d", &N);

    int arr[N]; // Declare an array with N elements

    // Optionally, initialize the array elements (for example purposes)
    for(int i = 0; i < N; i++) {
        arr[i] = i * 10; // Example: filling array with values 0, 10, 20, ..., (N-1)*10
    }

    // Loop through the array from index 0 to N-1 and print each element
    for(int i = 0; i < N; i++) {
        printf("Element at index %d: %d\n", i, arr[i]);
    }

    return 0;
}