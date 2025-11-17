#include <stdio.h>

struct Small {
    int field;
};

struct Large {
    char padding[64]; // Extra padding to make the size different from struct Small
    int other_field;
};

int main() {
    struct Large large = {{0}, 123}; // Initialize a struct Large with some values
    struct Small *s = (struct Small*)&large; // Type confusion: casting a pointer to one type to another

    s->field = 456; // Dereferencing the casted pointer and assigning a value

    printf("Value of field in struct Large after type confusion: %d\n", large.other_field);
    return 0;
}