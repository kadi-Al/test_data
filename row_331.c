#include <stdio.h>
#include <stdlib.h>

// Define the structure person
typedef struct {
    int id;
    float height;
    char name[50];
    int status;
} Person;

int main() {
    // Allocate memory for a new person
    Person* personPtr = (Person*)malloc(sizeof(Person));
    
    // Check if the memory allocation was successful
    if (personPtr == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    
    // Set the status field to 0
    personPtr->status = 0;
    
    // Example values for id, height, and name
    personPtr->id = 1;
    personPtr->height = 5.9f;
    sprintf(personPtr->name, "John Doe");
    
    // Print the details to verify
    printf("Person Details:\n");
    printf("ID: %d\n", personPtr->id);
    printf("Height: %.2f\n", personPtr->height);
    printf("Name: %s\n", personPtr->name);
    printf("Status: %d\n", personPtr->status);
    
    // Free the allocated memory
    free(personPtr);
    
    return 0;
}