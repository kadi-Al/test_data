#include <stdio.h>
#include <stdlib.h>

// Define the person struct
typedef struct {
    int age;
    char name[50];
} Person;

int main() {
    // Create a pointer to a Person
    Person *personPtr;

    // Allocate memory for one Person
    personPtr = (Person *)malloc(sizeof(Person));

    // Check if the memory allocation was successful
    if (personPtr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Set the members of the Person using the -> operator
    personPtr->age = 30;
    snprintf(personPtr->name, sizeof(personPtr->name), "John Doe");

    // Print out the information
    printf("Name: %s\n", personPtr->name);
    printf("Age: %d\n", personPtr->age);

    // Free the allocated memory
    free(personPtr);

    return 0;
}