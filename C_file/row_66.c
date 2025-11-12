pp
#include <iostream>
#include <limits>

int main() {
    int total = 100; // Example value for total
    int used = 200;   // Example value for used, causing underflow

    if (used > total) {
        std::cerr << "Error: 'used' is greater than 'total', leading to an underflow." << std::endl;
        return -1; // Exit with an error code
    }

    int remaining = total - used;
    std::cout << "Remaining after underflow handling: " << remaining << std::endl;

    // Handling overflow/underflow in mathematical operations can be done using libraries or manual checks.
    // Here's a simple example of checking before the operation and adjusting for potential issues:

    if (total > 0 && used > 0 && total < used) {
        std::cerr << "Warning: Potential underflow detected." << std::endl;
        remaining = std::numeric_limits<int>::min(); // Setting to the smallest possible int value as an extreme case.
    } else if (total < 0 && used < 0 && total > used) {
        std::cerr << "Warning: Potential overflow detected." << std::endl;
        remaining = std::numeric_limits<int>::max(); // Setting to the largest possible int value as an extreme case.
    } else {
        remaining = total - used;
    }

    std::cout << "Remaining after potential overflow/underflow handling: " << remaining << std::endl;

    return 0;
}