#include <stdio.h>

void work() {
    printf("Working...\n");
}

int main() {
    for (;;) {
        work();
    }
    return 0;
}