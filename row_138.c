#include <stdio.h>

int main() {
    int arr[10]; // Example: assume an array with 10 elements
    int input;

    printf("Enter an index (0-9): ");
    scanf("%d", &input);

    if (input >= 0 && input < 10) {
        arr[input] = 0;
        printf("arr[%d] is now set to 0.\n", input);
    } else {
        printf("Invalid index. Please enter an index between 0 and 9.\n");
    }

    return 0;
}