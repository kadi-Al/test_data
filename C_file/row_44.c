#include <stdio.h>
#include <stdlib.h> // For exit()

int main() {
    int arr[10];
    int user_index, value;

    printf("Enter the index (0-9) to store the value: ");
    scanf("%d", &user_index);

    if (user_index < 0 || user_index >= 10) {
        printf("Error: Index out of bounds.\n");
        exit(EXIT_FAILURE); // Exit program with error status
    } else {
        printf("Enter the value to store at index %d: ", user_index);
        scanf("%d", &value);
        arr[user_index] = value;
        printf("Value %d stored at index %d in array.\n", value, user_index);
    }

    // Optionally print the entire array to verify
    printf("Array contents: ");
    for (int i = 0; i < 10; i++) {
        printf("%d ", arr[i]);
    }
    printf("\n");

    return 0;
}