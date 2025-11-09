#include <stdio.h>
#include <stdlib.h>
#include <string.h>

typedef struct {
    int status;
    char name[20];
} Person;

int main() {
    // Allocate memory for a new person
    Person *person = (Person *)malloc(sizeof(Person));
    
    if (person == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    
    // Set the status to 0
    person->status = 0;
    
    // Set the name to "John"
    strcpy(person->name, "John");
    
    // Print the details of the person
    printf("Person Status: %d\n", person->status);
    printf("Person Name: %s\n", person->name);
    
    // Free the allocated memory
    free(person);
    
    return 0;
}