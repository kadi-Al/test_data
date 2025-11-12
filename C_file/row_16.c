#include <stdio.h>

int main() {
    int integer = 65; // ASCII value for 'A'
    char *str = (char*)integer; // Incorrect cast from int to char*
    printf("%s\n", str); // Corrected format specifier and string print statement
    return 0;
}