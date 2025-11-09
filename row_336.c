#include <stdio.h>
#include <stdlib.h> // For atoi function

int main(int argc, char *argv[]) {
    // Check if there are enough command line arguments
    if (argc <= 1) {
        printf("Usage: %s <index>\n", argv[0]);
        return 1;
    }

    // Convert the second argument to an integer
    int index = atoi(argv[1]);

    // Check if the conversion was successful and if the index is within bounds
    if (index >= argc || index < 0) {
        printf("Index out of bounds.\n");
        return 1;
    }

    // Print the argument at the specified index
    printf("%s\n", argv[index]);

    // Return 0 to indicate successful execution
    return 0;
}
bash
gcc -o cli_arg_printer cli_arg_printer.c
./cli_arg_printer 2