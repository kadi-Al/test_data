#include <stdio.h>

// Define a struct named Data
struct Data {
    int num;
    float fnum;
};

int main() {
    // Declare an instance of the struct Data without initializing it
    struct Data d;

    // Print the size of the uninitialized struct using sizeof
    printf("Size of struct Data: %zu bytes\n", sizeof(struct Data));

    return 0;
}