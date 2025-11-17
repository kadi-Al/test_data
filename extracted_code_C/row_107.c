pp
#include <iostream>

int main() {
    // Declare integers with values that would cause overflow
    int a = 2000000000;
    int b = 2000000000;
    
    // Perform addition which causes integer overflow
    int sum = a + b;
    
    // Output the result (which will be incorrect due to overflow)
    std::cout << "Sum: " << sum << std::endl;
    
    return 0;
}