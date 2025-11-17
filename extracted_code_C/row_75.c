pp
#include <iostream>

void safe_code() {
    std::cout << "This is safe code!" << std::endl;
}

int main() {
    if (0) { // This condition will always evaluate to false
        safe_code(); // This line will never be executed
    } else {
        std::cout << "Condition was false, so this is the executed block." << std::endl;
    }

    return 0;
}