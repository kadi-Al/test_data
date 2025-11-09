#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <number>\n", argv[0]);
        return 1;
    }

    // Convert command line argument to an integer
    int number = atoi(argv[1]);

    // Add 1000 to the number
    number += 1000;

    // Print the result
    printf("Result: %d\n", number);

    return 0;
}
bash
gcc -o add1000 add1000.c
./add1000 500  # This would output: Result: 1500