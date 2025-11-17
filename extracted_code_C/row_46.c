#include <stdio.h>

int main() {
    // Step 1: Define and initialize the integer array
    int int_array[5] = {10, 20, 30, 40, 50};

    // Step 2: Convert to char pointer and move by 4 positions (offsets for 4 * sizeof(int) bytes)
    char *cp = (char*)int_array;
    cp += 4 * sizeof(int); // Move the pointer forward by 4 integers worth of bytes

    // Step 3: Type punning to get the integer value from the memory location pointed by cp
    int val = *(int*)cp;

    // Print the result
    printf("The accessed integer value is: %d\n", val);

    return 0;
}