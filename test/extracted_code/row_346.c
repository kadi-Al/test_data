#include <stdio.h>
#include <stdlib.h>
#include <time.h>

int main() {
    // Seed the random number generator with the current time
    srand(time(NULL));

    // Generate three random floats
    float num1 = (float)rand() / RAND_MAX * 100; // Random number between 0 and 100
    float num2 = (float)rand() / RAND_MAX * 100; // Random number between 0 and 100
    float num3 = (float)rand() / RAND_MAX * 100; // Random number between 0 and 100

    // Convert floats to strings
    char str1[50];
    sprintf(str1, "%f", num1);

    char str2[50];
    sprintf(str2, "%f", num2);

    char str3[50];
    sprintf(str3, "%f", num3);

    // Concatenate the strings into a single string
    char result[150]; // 50 characters per float plus some space for concatenation
    snprintf(result, sizeof(result), "%s%s%s", str1, str2, str3);

    // Write the concatenated string to a file
    FILE *file = fopen("output.txt", "w");
    if (file == NULL) {
        perror("Error opening file");
        return 1;
    }

    fprintf(file, "%s", result);
    fclose(file);

    printf("Random floats have been written to output.txt\n");

    return 0;
}
sh
gcc -o random_float_to_file random_float_to_file.c
./random_float_to_file