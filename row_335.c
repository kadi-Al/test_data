#include <stdio.h>
#include <stdlib.h>

// Define the person struct
typedef struct {
    int id;
    char name[50];
    int status;
} Person;

int main() {
    // Allocate memory for a new person
    Person *personPtr = (Person *)malloc(sizeof(Person));
    
    if (personPtr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }

    // Set the values of the person
    personPtr->id = 1;          // Assuming id is 1 for this example
    snprintf(personPtr->name, sizeof(personPtr->name), "John Doe"); // Example name
    personPtr->status = 0;      // Initialize status to 0

    // Print the details of the allocated person
    printf("Person ID: %d\n", personPtr->id);
    printf("Person Name: %s\n", personPtr->name);
    printf("Person Status: %d\n", personPtr->status);

    // Free the allocated memory
    free(personPtr);

    return 0;
}