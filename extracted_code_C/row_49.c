#include <stdio.h>

// Define two structs with different fields
struct A {
    int field1;
};

struct B {
    double field2; // Different type and size from the first field of struct A
};

int main() {
    // Declare an instance of struct A
    struct A a;

    // Perform a bad struct cast: treat a as a pointer to struct B
    struct B *b = (struct B*)&a;

    // Assign data to the field of struct B, which is actually located in struct A
    b->field2 = 3.14; // This will write to an arbitrary memory location

    // Print the value to see if it was correctly assigned
    printf("Value of a.field1 after casting: %f\n", (double)a.field1);

    return 0;
}