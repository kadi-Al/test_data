#include <stdio.h>
#include <stdlib.h>

// Define the struct person
typedef struct {
    int age;
    char name[50];
    float height;
    int status; // Added field for clarity
} Person;

int main() {
    // Allocate memory for a new person
    Person *personPtr = (Person *)malloc(sizeof(Person));
    
    if (personPtr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Initialize the status field to 0
    personPtr->status = 0;

    // Print the allocated memory address and initialized status
    printf("Allocated memory for a new person at address: %p\n", (void *)personPtr);
    printf("Initialized status field to: %d\n", personPtr->status);

    // Free the allocated memory when done
    free(personPtr);

    return 0;
}