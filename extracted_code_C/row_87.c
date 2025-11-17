#include <stdio.h>

int main() {
    int num;
    
    // Prompt the user for input
    printf("Enter an integer: ");
    
    // Read the integer from the standard input (stdin)
    scanf("%d", &num);
    
    // Print the read integer
    printf("You entered: %d\n", num);
    
    return 0;
}
sh
gcc -o read_int read_int.c
./read_int