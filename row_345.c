#include <stdio.h>
#include <stdlib.h>
#include <time.h>

// Function to convert a float to a string
char* floatToString(float num) {
    // Allocate memory for the string (enough space for 24 characters including null terminator)
    char* str = (char*)malloc(24 * sizeof(char));
    if (str == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        exit(1);
    }
    // Convert the float to a string using sprintf
    snprintf(str, 24, "%f", num);
    return str;
}

int main() {
    // Seed the random number generator with the current time
    srand(time(NULL));

    // Generate three random floats
    float rand_a = (float)rand() / RAND_MAX * 100.0f; // Random float between 0 and 100
    float rand_b = (float)rand() / RAND_MAX * 100.0f; // Random float between 0 and 100
    float rand_c = (float)rand() / RAND_MAX * 100.0f; // Random float between 0 and 100

    // Convert the floats to strings using the custom function
    char* str_a = floatToString(rand_a);
    char* str_b = floatToString(rand_b);
    char* str_c = floatToString(rand_c);

    // Print the resulting strings
    printf("str_a: %s\n", str_a);
    printf("str_b: %s\n", str_b);
    printf("str_c: %s\n", str_c);

    // Free the allocated memory for the strings
    free(str_a);
    free(str_b);
    free(str_c);

    return 0;
}
sh
gcc -o random_float_to_string random_float_to_string.c
./random_float_to_string