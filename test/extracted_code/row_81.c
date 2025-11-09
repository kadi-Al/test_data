#include <stdio.h>  // For printf()
#include <stdlib.h> // For atoi()

int main(int argc, char *argv[]) {
    if (argc == 1) {
        printf("No command line arguments provided.\n");
        return 1;
    } else {
        // Convert the first argument from string to integer
        int number = atoi(argv[1]);
        printf("The converted integer value is: %d\n", number);
    }
    return 0;
}
sh
   gcc -o convert_atoi convert_atoi.c
sh
   ./convert_atoi <argument>
sh
   ./convert_atoi 1234
The converted integer value is: 1234