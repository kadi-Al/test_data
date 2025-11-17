#include <stdio.h>

int main() {
    // Define an array
    int array[] = {10, 20, 30, 40, 50};
    
    // Specify the position in the array
    int pos = 2;
    
    // Use pointer arithmetic to get a pointer to the element at 'pos'
    int *ptr = &array[pos];
    
    // Assign data to the location pointed by ptr
    int data = 100;
    *ptr = data;
    
    // Print the array to verify the change
    for (int i = 0; i < 5; i++) {
        printf("%d ", array[i]);
    }
    
    return 0;
}