#include <stdio.h>

void checkCondition(int x) {
    if (x > 10 && x < 5) {
        printf("The condition x > 10 && x < 5 is true.\n");
    } else {
        printf("The condition x > 10 && x < 5 is false.\n");
    }
}

int main() {
    int testValue = 7; // Example value to check
    checkCondition(testValue);
    return 0;
}