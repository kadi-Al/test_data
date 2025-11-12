#include <stdio.h>

// Recursive function to calculate factorial
unsigned long long factorial(int n) {
    if (n == 0 || n == 1) {
        return 1;
    } else {
        return n * factorial(n - 1);
    }
}

int main() {
    int number = 50; // You can change this number to test different cases
    unsigned long long result;

    printf("Calculating the factorial of %d...\n", number);
    result = factorial(number);
    printf("The factorial of %d is %llu\n", number, result);

    return 0;
}
bash
gcc -o factorial_recursive factorial_recursive.c
./factorial_recursive