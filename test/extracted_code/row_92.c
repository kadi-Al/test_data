#include <stdio.h>
#include <stdlib.h>

typedef struct {
    int age;
    char name[50];
} Person;

int main() {
    // Allocate memory for a new person
    Person *p = (Person *)malloc(sizeof(Person));
    
    if (p == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    p->age = 25; // Accessing struct members using the -> operator
    sprintf(p->name, "John Doe"); // Example of setting name

    printf("Name: %s\n", p->name);
    printf("Age: %d\n", p->age);

    // Free the allocated memory
    free(p);
    
    return 0;
}
typedef struct {
    int age;
    char name[50];
    int status; // Additional field for status
} Person;

int main() {
    // Allocate memory for a new person
    Person *p = (Person *)malloc(sizeof(Person));
    
    if (p == NULL) {
        printf("Memory allocation failed!\n");
        return 1;
    }

    p->age = 25; // Accessing struct members using the -> operator
    sprintf(p->name, "John Doe"); // Example of setting name
    p->status = 0; // Setting status to 0

    printf("Name: %s\n", p->name);
    printf("Age: %d\n", p->age);
    printf("Status: %d\n", p->status);

    // Free the allocated memory
    free(p);
    
    return 0;
}