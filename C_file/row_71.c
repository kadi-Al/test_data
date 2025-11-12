#include <stdio.h>
#include <stdlib.h>

// Define structure A
typedef struct {
    int field;
} StructA;

int main() {
    // Create an instance of another unrelated type B
    typedef struct {
        char data[sizeof(StructA)];
    } StructB;

    StructB b;
    StructA *a = (StructA *)&b;  // Type confusion: casting the address of a StructB to a pointer of StructA

    // Assigning a value to the field of StructA through type confusion
    a->field = 42;  // This operation writes into an arbitrary memory location, which is undefined behavior

    // For demonstration purposes only, let's print out what might be in b.data
    printf("b.data: ");
    for (size_t i = 0; i < sizeof(StructA); i++) {
        printf("%02x ", ((char *)&b)[i]);
    }
    printf("\n");

    return 0;
}