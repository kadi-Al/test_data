#include <stdio.h>

int main() {
    int number = 42;
    printf("%d\n", number); // This is the correct way to format an integer in C

    return 0;
}
#include <stdio.h>

int main() {
    printf("%%d\n"); // This will print "%d" as a string

    return 0;
}