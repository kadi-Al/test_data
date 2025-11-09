#include <stdio.h>
#include <stdlib.h>

// Define the structure person
typedef struct {
    int age;
    char name[50];
    float height;
    int status; // New member for status
} Person;

int main() {
    // Declare a pointer to a person
    Person *personPtr;

    // Allocate memory for the new person using sizeof(Person)
    personPtr = (Person *)malloc(sizeof(Person));

    // Check if memory allocation was successful
    if (personPtr == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    // Set the values for the new person
    personPtr->age = 30;      // Using -> operator to access struct members through a pointer
    personPtr->height = 5.8f; // Using -> operator to access struct members through a pointer
    sprintf(personPtr->name, "John Doe"); // Assuming name is a char array and using sprintf for demonstration
    personPtr->status = 0;   // Setting the status to 0

    // Print the values to verify
    printf("Person Details:\n");
    printf("Name: %s\n", personPtr->name);
    printf("Age: %d\n", personPtr->age);
    printf("Height: %.2f\n", personPtr->height);
    printf("Status: %d\n", personPtr->status);

    // Free the allocated memory
    free(personPtr);

    return 0;
}