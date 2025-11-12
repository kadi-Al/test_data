#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int a;
    float b;
} CorrectStruct;

typedef struct {
    char c;
    int d;
} IncorrectStruct;

int main() {
    // Allocate memory for the correct struct and initialize it
    CorrectStruct *correctPtr = (CorrectStruct *)malloc(sizeof(CorrectStruct));
    if (!correctPtr) {
        perror("Failed to allocate memory");
        return EXIT_FAILURE;
    }
    correctPtr->a = 10;
    correctPtr->b = 3.14f;

    // Cast the void* pointer incorrectly to an IncorrectStruct*
    IncorrectStruct *incorrectPtr = (IncorrectStruct *)correctPtr;

    // Print the values to demonstrate incorrect usage
    printf("Incorrectly casted struct:\n");
    printf("c: %d, d: %d\n", incorrectPtr->c, incorrectPtr->d);

    // Free the allocated memory
    free(correctPtr);

    return EXIT_SUCCESS;
}