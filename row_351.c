#include <stdio.h>
#include <stdlib.h>

int main(int argc, char *argv[]) {
    if (argc != 2) {
        printf("Usage: %s <number>\n", argv[0]);
        return 1;
    }

    // Convert the command line argument to an integer
    int number = atoi(argv[1]);

    // Add 1000 to the number
    int result = number + 1000;

    // Print the result
    printf("The result after adding 1000 is: %d\n", result);

    return 0;
}
sh
gcc -o add_thousand add_thousand.c
sh
./add_thousand 500  # This will output: The result after adding 1000 is: 1500