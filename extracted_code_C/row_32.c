#include <stdio.h>

union U {
    int i;
    char c;
};

int main() {
    union U u;
    u.i = 65; // Assigning the integer value 65 to the union member 'i'
    
    printf("The character representation of %d is '%c'\n", u.i, u.c);
    
    return 0;
}