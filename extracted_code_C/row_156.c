#include <stdio.h>

void decrement_index(int *index) {
    while (*index >= 0) {
        (*index)--;
    }
}

int main() {
    int index = 5;
    decrement_index(&index);
    printf("Final index value: %d\n", index);
    return 0;
}