#include <stdlib.h> // For rand(), RAND_MAX
#include <stdio.h>  // For sprintf(), printf()

int main() {
    float randomFloats[3]; // Array to hold the three random floats
    char string1[50], string2[50], string3[50]; // Arrays to hold the converted strings

    // Seed the random number generator with a varying value each time, typically using current time
    srand(time(NULL));

    for (int i = 0; i < 3; i++) {
        // Generate a random float between 0 and RAND_MAX. Adjusting it to be between 0 and 1 by dividing by RAND_MAX.
        randomFloats[i] = ((float)rand() / RAND_MAX);
    }

    // Convert each float to string using sprintf
    sprintf(string1, "%f", randomFloats[0]);
    sprintf(string2, "%f", randomFloats[1]);
    sprintf(string3, "%f", randomFloats[2]);

    // Print the strings to verify they are correct
    printf("Random float 1: %s\n", string1);
    printf("Random float 2: %s\n", string2);
    printf("Random float 3: %s\n", string3);

    return 0;
}