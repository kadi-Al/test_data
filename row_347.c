#include <stdio.h>
#include <stdlib.h> // For atoi()

int main(int argc, char *argv[]) {
    if (argc == 1) {
        printf("No command line arguments provided.\n");
        return 1;
    } else {
        int sum = 0;
        for (int i = 1; i < argc; i++) { // Start from 1 to skip the program name
            int num = atoi(argv[i]); // Convert string argument to integer
            if (num == 0 && argv[i][0] != '0') { // Check for invalid input conversion
                printf("Invalid number: %s\n", argv[i]);
                return 1;
            } else {
                sum += num;
            }
        }
        printf("Sum of the provided numbers: %d\n", sum);
    }
    return 0;
}
bash
gcc -o convert_and_sum convert_and_sum.c
bash
./convert_and_sum 10 20 30 "40"
Sum of the provided numbers: 100