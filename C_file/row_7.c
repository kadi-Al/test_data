#include <stdio.h>

void deadFunction() {
    printf("This will not run because of the return.\n");
    return; // This stops the execution of the function and prevents any further code from running
}

int main() {
    deadFunction();
    printf("This line is unreachable due to the early return in deadFunction.\n");
    return 0;
}