#include <stdio.h>
#include <stdlib.h>

// Define a person struct with one member: status
typedef struct {
    int status;
} Person;

int main() {
    // Allocate memory for a new person
    Person* p = (Person*)malloc(sizeof(Person));
    
    // Check if the allocation was successful
    if (p == NULL) {
        fprintf(stderr, "Memory allocation failed\n");
        return 1;
    }
    
    // Initialize the status to 0
    p->status = 0;
    
    // Print the status to verify
    printf("Status: %d\n", p->status);
    
    // Free the allocated memory
    free(p);
    
    return 0;
}