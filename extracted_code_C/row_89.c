pp
#include <iostream>

void impossible() {
    std::cout << "This should never happen!" << std::endl;
}

int main() {
    int x = 10; // You can set any value to x, the condition will always be false.

    if (x > 0 && x < 0) {
        impossible();
    } else {
        std::cout << "The condition is not true." << std::endl;
    }

    return 0;
}